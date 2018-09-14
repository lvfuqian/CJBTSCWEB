package com.elegps.tscweb.comm;

public class CnToPYAlpha {

	private static final char[] alphatable =

	{

	'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2',
			'3', '4', '5', '6', '7', '8', '9', '0'

	};

	private static final int[] table = new int[] {

	45217, 45253, 45761, 46318, 46826,

	47010, 47297, 47614, 47614, 48119,

	49062, 49324, 49896, 50371, 50614,

	50622, 50906, 51387, 51446, 52218,

	52218, 52218, 52698, 52980, 53689, 54481, 55289 };

	public CnToPYAlpha() {

	}

	/**
	 * 
	 * 主函数, 输入字符, 得到他的声母, 英文字母返回对应的大写字母 其他非简体汉字返回 '*'
	 */

	public static char Char2Alpha(char ch) {

		if (ch >= 'a' && ch <= 'z')

			return (char) (ch - 'a' + 'A');

		if (ch >= 'A' && ch <= 'Z')

			return ch;
		if (ch >= '0' && ch <= '9')
			return ch;
		int gb = gbValue(ch);

		if (gb < table[0])

			return '*';

		for (int i = 0; i < 36; ++i) {

			if (match(i, gb)) {

				if (i >= 36)

					return '*';

				else

					return alphatable[i];
			}
		}
		return '*';
	}

	/**
	 * 
	 * 根据一个包含汉字的字符串返回一个汉字拼音首字母的字符串
	 */

	public static String String2Alpha(String str) {
		String Result = "";
		try {
			for (int i = 0; i < str.length(); i++) {
				Result += Char2Alpha(str.charAt(i));
			}
		} catch (Exception e) {
			Result = " ";
		}

		return Result;

	}

	private static boolean match(int i, int gb) {
		if (gb < table[i])
			return false;
		int j = i + 1;
		// 字母Z使用了两个标签
		while (j < 36 && (table[j] == table[i]))
			++j;
		if (j == 36)
			return gb <= table[j];
		else
			return gb < table[j];

	}

	/**
	 * 
	 * 取出传入汉字的编码
	 */

	private static int gbValue(char ch) {
		String str = new String();
		str += ch;
		try {
			byte[] bytes = str.getBytes("GB2312");
			if (bytes.length < 2)
				return 0;
			return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
		} catch (Exception e) {
			return '*';
		}

	}
	/**
	 * 去除字符串中的其他符号
	 * 并且保留从0到n位的字符
	 * @param str
	 * @return
	 */
	public static String getFive(String str,int n) {
		StringBuilder five =new StringBuilder();
		char[] ss=String2Alpha(str).toCharArray();
		for (int i = 0; i < ss.length; i++) {
			if(ss[i]!='*')
				five.append(ss[i]);
		}		
		if(five.toString().length()<5){
			return five.toString().toLowerCase();
		}
		return five.toString().toLowerCase().substring(0, n);
	}

	/**
	 * 
	 * 测试输出
	 */

	public static void main(String[] args) {

		CnToPYAlpha gb2A = new CnToPYAlpha();
		System.out.println(getFive("人保大厦",5));
		System.out.println("山东淄博001:\t"
				+ gb2A.String2Alpha("人民银行").toLowerCase());// 输出
		// HSTF*

	}

}