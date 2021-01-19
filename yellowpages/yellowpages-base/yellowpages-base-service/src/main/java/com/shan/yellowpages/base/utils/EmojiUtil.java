//package com.shan.yellowpages.base.utils;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//import org.apache.commons.lang3.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.vdurmont.emoji.EmojiParser;
//
//public class EmojiUtil {
//
//	private static Logger logger = LoggerFactory.getLogger(EmojiUtil.class);
//
//	private static Pattern emoji = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]",
//			Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
//	/**
//	 * 网上流行的方法，经实测不能完全过滤emoji，废弃
//	 *
//	 * @param source 源字符串
//	 * @param slipStr 替换字符
//	 * @return string
//	 */
//	@Deprecated
//	public static String filterEmoji(String source, String slipStr) {
//		logger.trace("[EmojiUtil][filterEmoji] entering, source: {},slipStr:{}", source, slipStr);
//		String result = "";
//		if (StringUtils.isNotBlank(source)) {
//
//			Matcher emojiMatcher = emoji.matcher(source);
//			if (emojiMatcher.find()) {
//				// 含有emoji表情
//				result = emojiMatcher.replaceAll(slipStr);
//			} else {
//				result = source;
//			}
//		}
//		logger.info("[EmojiUtil][filterEmoji] result:{}, source: {},slipStr:{}", result, source, slipStr);
//		return result;
//	}
//
//	/**
//	 * 过滤emoji表情
//	 *
//	 * @param source
//	 * @return
//	 */
//	public static String filterEmoji(String source) {
//		if (StringUtils.isNotBlank(source)) {
//			return EmojiParser.removeAllEmojis(source);
//		}
//		return null;
//	}
//
//	public static void main(String[] args) {
//		String emojiText1 = "💪";
//		String emojiText2 = "⌚️\uD83D\uDCF1\uD83D\uDCF2\uD83D\uDCBB⌨️\uD83D\uDDA5\uD83D\uDDA8\uD83D\uDDB1\uD83D\uDDB2\uD83D\uDD79\uD83D\uDDDC\uD83D\uDCBD\uD83D\uDCBE\uD83D\uDCBF\uD83D\uDCC0\uD83D\uDCFC\uD83D\uDCF7\uD83D\uDCF8\uD83D\uDCF9\uD83C\uDFA5\uD83D\uDCFD\uD83C\uDF9E\uD83D\uDCDE☎️\uD83D\uDCDF\uD83D\uDCE0\uD83D\uDCFA\uD83D\uDCFB\uD83C\uDF99\uD83C\uDF9A\uD83C\uDF9B⏱⏲⏰\uD83D\uDD70⌛️⏳\uD83D\uDCE1\uD83D\uDD0B\uD83D\uDD0C\uD83D\uDCA1\uD83D\uDD26\uD83D\uDD6F\uD83D\uDDD1\uD83D\uDEE2\uD83D\uDCB8\uD83D\uDCB5\uD83D\uDCB4\uD83D\uDCB6\uD83D\uDCB7\uD83D\uDCB0\uD83D\uDCB3\uD83D\uDC8E⚖️\uD83D\uDD27\uD83D\uDD28⚒\uD83D\uDEE0⛏\uD83D\uDD29⚙️⛓\uD83D\uDD2B\uD83D\uDCA3\uD83D\uDD2A\uD83D\uDDE1⚔️\uD83D\uDEE1\uD83D\uDEAC⚰️⚱️\uD83C\uDFFA\uD83D\uDD2E\uD83D\uDCFF\uD83D\uDC88⚗️\uD83D\uDD2D\uD83D\uDD2C\uD83D\uDD73\uD83D\uDC8A\uD83D\uDC89\uD83C\uDF21\uD83D\uDEBD\uD83D\uDEB0\uD83D\uDEBF\uD83D\uDEC1\uD83D\uDEC0\uD83D\uDECE\uD83D\uDD11\uD83D\uDDDD\uD83D\uDEAA\uD83D\uDECB\uD83D\uDECF\uD83D\uDECC\uD83D\uDDBC\uD83D\uDECD\uD83D\uDED2\uD83C\uDF81\uD83C\uDF88\uD83C\uDF8F\uD83C\uDF80\uD83C\uDF8A\uD83C\uDF89\uD83C\uDF8E\uD83C\uDFEE\uD83C\uDF90✉️\uD83D\uDCE9\uD83D\uDCE8\uD83D\uDCE7\uD83D\uDC8C\uD83D\uDCE5\uD83D\uDCE4\uD83D\uDCE6\uD83C\uDFF7\uD83D\uDCEA\uD83D\uDCEB\uD83D\uDCEC\uD83D\uDCED\uD83D\uDCEE\uD83D\uDCEF\uD83D\uDCDC\uD83D\uDCC3\uD83D\uDCC4\uD83D\uDCD1\uD83D\uDCCA\uD83D\uDCC8\uD83D\uDCC9\uD83D\uDDD2\uD83D\uDDD3\uD83D\uDCC6\uD83D\uDCC5\uD83D\uDCC7\uD83D\uDDC3\uD83D\uDDF3\uD83D\uDDC4\uD83D\uDCCB\uD83D\uDCC1\uD83D\uDCC2\uD83D\uDDC2\uD83D\uDDDE\uD83D\uDCF0\uD83D\uDCD3\uD83D\uDCD4\uD83D\uDCD2\uD83D\uDCD5\uD83D\uDCD7\uD83D\uDCD8\uD83D\uDCD9\uD83D\uDCDA\uD83D\uDCD6\uD83D\uDD16\uD83D\uDD17\uD83D\uDCCE\uD83D\uDD87\uD83D\uDCD0\uD83D\uDCCF\uD83D\uDCCC\uD83D\uDCCD✂️\uD83D\uDD8A\uD83D\uDD8B✒️\uD83D\uDD8C\uD83D\uDD8D\uD83D\uDCDD✏️\uD83D\uDD0D\uD83D\uDD0E\uD83D\uDD0F\uD83D\uDD10\uD83D\uDD12\uD83D\uDD13";
//		String emojiText3 = "\uD83D\uDE01\uD83D\uDE02\uD83D\uDE03\uD83D\uDE04\uD83D\uDC7F\uD83D\uDE09\uD83D\uDE0A☺\uD83D\uDE0C\uD83D\uDE0D\uD83D\uDE0F\uD83D\uDE12\uD83D\uDE13\uD83D\uDE14\uD83D\uDE16\uD83D\uDE18\uD83D\uDE1A\uD83D\uDE1C\uD83D\uDE1D\uD83D\uDE1E\uD83D\uDE20\uD83D\uDE21\uD83D\uDE22\uD83D\uDE23\uD83D\uDE25\uD83D\uDE28\uD83D\uDE2A\uD83D\uDE2D\uD83D\uDE30\uD83D\uDE31\uD83D\uDE32\uD83D\uDE33\uD83D\uDE37\uD83D\uDC6F\uD83D\uDC76\uD83D\uDC66\uD83D\uDC67\uD83D\uDC68\uD83D\uDC69\uD83D\uDC6B\uD83D\uDC71\uD83D\uDC72\uD83D\uDC73\uD83D\uDC74\uD83D\uDC75\uD83D\uDC6E\uD83D\uDC77\uD83D\uDC78\uD83D\uDC82\uD83D\uDC7C\uD83C\uDF85\uD83D\uDC7B\uD83D\uDCA9\uD83D\uDC80\uD83D\uDC7D\uD83D\uDC7E\uD83D\uDC81\uD83D\uDE45\uD83D\uDE46\uD83D\uDC86\uD83D\uDC87\uD83D\uDC91\uD83D\uDC8F\uD83D\uDE4C\uD83D\uDC4F\uD83D\uDC42\uD83D\uDC40\uD83D\uDC43\uD83D\uDC44\uD83D\uDC8B\uD83D\uDC85\uD83D\uDC4B\uD83D\uDC4D\uD83D\uDC4E\uD83D\uDC46\uD83D\uDC47\uD83D\uDC48\uD83D\uDC49\uD83D\uDC4C✌\uD83D\uDC4A✊\uD83D\uDCAA\uD83D\uDC50\uD83D\uDE4F";
//		String emojiText4 = "\uD83D\uDCBB\uD83D\uDCF7\uD83D\uDCFA\uD83D\uDCFB\uD83D\uDCE0\uD83D\uDCBE\uD83D\uDCC0\uD83D\uDCFC\uD83D\uDCA1\uD83D\uDC8E\uD83C\uDF02\uD83D\uDC61\uD83D\uDC84\uD83D\uDC62\uD83D\uDC57\uD83D\uDC5A\uD83D\uDEBD\uD83D\uDCEC\uD83D\uDCA3\uD83D\uDC8A\uD83D\uDCE3\uD83D\uDD0A㊗\uD83C\uDE35\uD83C\uDE36\uD83C\uDE38\uD83C\uDE3A\uD83C\uDE39\uD83C\uDE33\uD83C\uDE01✳✴\uD83C\uDD8E\uD83C\uDD7E\uD83C\uDD7F\uD83D\uDEBE\uD83C\uDD92\uD83C\uDD97\uD83C\uDD99♉♊♋♍♐♐♒♒♓\uD83D\uDEB9\uD83D\uDEBC▶◀➡⬆↗↘↖#⃣0⃣0⃣1⃣1⃣2⃣3⃣4⃣7⃣\uD83C\uDFA6™™©\uD83D\uDCB1➿〽❗❓❕❕❌⭕\uD83D\uDD1D⛎\uD83D\uDD30\uD83D\uDD31⚠\uD83D\uDCA2♥♦⬜⬜\uD83D\uDD52\uD83D\uDD53\uD83D\uDD55\uD83D\uDD56\uD83D\uDD57\uD83D\uDD58\uD83D\uDD5A\uD83D\uDD12\uD83D\uDD13\uD83D\uDD11";
//		String emojiText5 = "\uD83D\uDE04\uD83D\uDE03\uD83D\uDE00\uD83D\uDE0A☺\uD83D\uDE09\uD83D\uDE0D\uD83D\uDE18\uD83D\uDE1A\uD83D\uDE17\uD83D\uDE19\uD83D\uDE1C\uD83D\uDE1D\uD83D\uDE1B\uD83D\uDE33\uD83D\uDE01\uD83D\uDE14\uD83D\uDE0C\uD83D\uDE12\uD83D\uDE1E\uD83D\uDE23\uD83D\uDE22\uD83D\uDE02\uD83D\uDE2D\uD83D\uDE2A\uD83D\uDE25\uD83D\uDE30\uD83D\uDE05\uD83D\uDE13\uD83D\uDE29\uD83D\uDE2B\uD83D\uDE28\uD83D\uDE31\uD83D\uDE20\uD83D\uDE21\uD83D\uDE24\uD83D\uDE16\uD83D\uDE06";
//
//		logger.info("[EmojiUtil][main]filterEmoji result:{}", filterEmoji(emojiText1));
//		logger.info("[EmojiUtil][main]EmojiParser result:{}", EmojiParser.removeAllEmojis(emojiText1));
//
//		logger.info("[EmojiUtil][main]filterEmoji result:{}", filterEmoji(emojiText2));
//		logger.info("[EmojiUtil][main]EmojiParser result:{}", EmojiParser.removeAllEmojis(emojiText2));
//		//
//		logger.info("[EmojiUtil][main]filterEmoji result:{}", filterEmoji(emojiText4));
//		logger.info("[EmojiUtil][main]EmojiParser result:{}", EmojiParser.removeAllEmojis(emojiText4));
//
//		logger.info("[EmojiUtil][main]filterEmoji result:{}", filterEmoji(emojiText5));
//		logger.info("[EmojiUtil][main]EmojiParser result:{}", EmojiParser.removeAllEmojis(emojiText5));
//	}
//}
