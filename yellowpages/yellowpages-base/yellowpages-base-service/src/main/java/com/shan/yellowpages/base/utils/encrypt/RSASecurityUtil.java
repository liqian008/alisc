/*
 * Copyright (c) 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shan.yellowpages.base.utils.encrypt;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shan.yellowpages.base.utils.Base64;

/**
 * Security-related methods. For a secure implementation, all of this code
 * should be implemented on a server that communicates with the application on
 * the device.
 * 根据google play代码修改，用以验签
 * 
 * @see <a href=
 *      "https://github.com/googlesamples/android-play-billing/blob/master/TrivialDrive_v2/shared-module/src/main/java/com/example/billingmodule/billing/BillingManager.java">google
 *      play 示例代码</a>
 * 
 */
public class RSASecurityUtil {
	private static final String TAG = "IABUtil/Security";

	private static final String KEY_FACTORY_ALGORITHM = "RSA";
	private static final String SIGNATURE_ALGORITHM = "SHA1withRSA";

	private static final Logger logger = LoggerFactory.getLogger(Security.class);

	/**
	 * Verifies that the data was signed with the given signature, and returns the
	 * verified purchase.
	 * 
	 * @param base64PublicKey the base64-encoded public key to use for verifying.
	 * @param signedData the signed JSON string (signed, not encrypted)
	 * @param signature the signature for the data, signed with the private key
	 * @throws IOException if encoding algorithm is not supported or key
	 *             specification is invalid
	 */
	public static boolean verifyPurchase(String base64PublicKey, String signedData, String signature)
			throws IOException {
		if (StringUtils.isEmpty(signedData) || StringUtils.isEmpty(base64PublicKey) || StringUtils.isEmpty(signature)) {
			logger.warn(TAG, "Purchase verification failed: missing data.");
			return false;
		}

		PublicKey key = generatePublicKey(base64PublicKey);
		return verify(key, signedData, signature);
	}

	/**
	 * Generates a PublicKey instance from a string containing the Base64-encoded
	 * public key.
	 *
	 * @param encodedPublicKey Base64-encoded public key
	 * @throws IOException if encoding algorithm is not supported or key
	 *             specification is invalid
	 */
	public static PublicKey generatePublicKey(String encodedPublicKey) throws IOException {
		try {
			byte[] decodedKey = Base64.decode(encodedPublicKey);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_FACTORY_ALGORITHM);
			return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
		} catch (NoSuchAlgorithmException e) {
			// "RSA" is guaranteed to be available.
			throw new RuntimeException(e);
		} catch (InvalidKeySpecException e) {
			String msg = "Invalid key specification: " + e;
			logger.warn(TAG, msg);
			throw new IOException(msg);
		}
	}

	/**
	 * Verifies that the signature from the server matches the computed signature on
	 * the data. Returns true if the data is correctly signed.
	 *
	 * @param publicKey public key associated with the developer account
	 * @param signedData signed data from server
	 * @param signature server signature
	 * @return true if the data and signature match
	 */
	public static boolean verify(PublicKey publicKey, String signedData, String signature) {
		byte[] signatureBytes;
		try {
			signatureBytes = Base64.decode(signature);
		} catch (IllegalArgumentException e) {
			logger.warn(TAG, "Base64 decoding failed.");
			return false;
		}
		try {
			Signature signatureAlgorithm = Signature.getInstance(SIGNATURE_ALGORITHM);
			signatureAlgorithm.initVerify(publicKey);
			signatureAlgorithm.update(signedData.getBytes());
			if (!signatureAlgorithm.verify(signatureBytes)) {
				logger.warn(TAG, "Signature verification failed.");
				return false;
			}
			return true;
		} catch (NoSuchAlgorithmException e) {
			// "RSA" is guaranteed to be available.
			throw new RuntimeException(e);
		} catch (InvalidKeyException e) {
			logger.warn(TAG, "Invalid key specification.");
		} catch (SignatureException e) {
			logger.warn(TAG, "Signature exception.");
		}
		return false;
	}
}