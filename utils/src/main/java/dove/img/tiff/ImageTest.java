package dove.img.tiff;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;

/**
 * Created by E1041 on 2020/3/4.
 */
public class ImageTest {
	public static void main(String[] args) {
		//PDFUtil.image2PDF("C:\\Users\\E1041\\Desktop\\esan\\word_true.pdf","C:\\Users\\E1041\\Desktop\\esan\\44444.pdf");
		String iamgeString = GetImageStr("C:\\Users\\E1041\\Desktop\\esan\\1111.jpg");
	/*	char[] arr =iamgeString.toCharArray();
		char[] arr2 =iamgeString.toCharArray();
		for(int i=0;i<arr.length/2;i++)
		{
			char temp;
			temp=arr2[i];
			arr2[i]=arr2[arr.length-i-1];
			arr2[arr.length-i-1]=temp;
		}
		System.out.println(new String(arr));
		System.out.println("===================================================");
		System.out.println(new String(arr2));*/

		boolean b = GenerateImage(iamgeString, "C:\\Users\\E1041\\Desktop\\esan\\1111.png");
		System.out.println(b);

	}

	public static String GetImageStr(String imgFilePath) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
		byte[] data = null;

		// 读取图片字节数组
		try {
			InputStream in = new FileInputStream(imgFilePath);
			/*inStream.available()读取文件流长度不不完整，这个方法从本地文件读取数据时一般不会出现问题，
			 但是通过网路传输就会出现图片传输不完整的情况，因为网络通讯是间断性的一串字节往往分几批进行发送。
			本地程序调用available()方法有时得到0，这可能是对方还没有响应，也可能是对方已经响应了，但是数据还没有送达本地。*/
			data = new byte[in.available()];
			System.out.println(data);
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 对字节数组Base64编码   问题:为甚么要对字节流进行Base64 进行编码
		/*http协议可以传输二进制数据（浏览器里面的图片、视频、音频都是二进制数据），
		问题是传输的时候必须保证字节不会改变。你直接将图片的字节转化为一个字符串一般是不行的，
		除非使用的字符集在0~255之间全部有对应的字符，否则就会导致某些字节转换错误，
		那么客户端所收到的就不再是原来的那些字节了。base64之所以可以，
		是因为编码之后的字符都落在ASCII区间，而ASCII是所有字符编码都会支持的，因此可以正确传输和还原。*/

		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	/*优化字节流读取
	*
	* */
	public static String GetImageStrByByteArrayOutputStream(String imgPath) throws IOException {
		// 通过输入流获取图片数据
		File file = new File(imgPath);
		InputStream inStream = new FileInputStream(file);

		//得到图片的二进制数据，以二进制封装得到数据，具有通用性

		ByteArrayOutputStream outStream = new ByteArrayOutputStream();

		//创建一个Buffer字符串

		byte[] buffer = new byte[1024];

		//每次读取的字符串长度，如果为-1，代表全部读取完毕

		int len = 0;

		//使用一个输入流从buffer里把数据读取出来

		while ((len = inStream.read(buffer)) != -1) {
			//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		//关闭输入,输出流
		inStream.close();
		outStream.close();
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	public static boolean GenerateImage(String imgStr, String imgFilePath) {// 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
