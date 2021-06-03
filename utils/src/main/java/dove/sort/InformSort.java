package dove.sort;

import java.util.TreeSet;

/**
 * Created by E1041 on 2020/1/15.
 */
public class InformSort {
	public static void main(String[] args) {
		TreeSet<InformInfo> lists = new TreeSet<InformInfo>();

		InformInfo informInfo = new InformInfo(
				"", "", "2019-05-15",
				"UN", "補充資料通知書", "123456", true, 0);
		InformInfo informInfo6 = new InformInfo(
				"", "", "",
				"UN", "補充資料通知書", "123456", true, 0);
		InformInfo informInfo2 = new InformInfo(
				"", "", "2019-02-10",
				"UNP", "核保處理通知書", "12345678", true, 0);

		InformInfo informInfo3 = new InformInfo(
				"", "", "2019-02-10",
				"UNP", "核保處理通知書", "1234567890", true, 0);
		InformInfo informInfo4 = new InformInfo(
				"", "", "2019-05-15",
				"UNP","核保處理通知書",  "1234567890", true, 0);
		InformInfo informInfo5 = new InformInfo(
				"", "", "",
				"UNP","核保處理通知書",  "1234567890", true, 0);
		InformInfo informInfo7 = new InformInfo(
				"2020-01-15", "", "",
				"UNP","繳費通知書",  "12345", false, 0);
		InformInfo informInfo8 = new InformInfo(
				"2020-01-18", "", "",
				"UNP","保單週年報表",  "123456789", false, 0);

		lists.add(informInfo);
		lists.add(informInfo2);
		lists.add(informInfo3);
		lists.add(informInfo4);
		lists.add(informInfo5);
		lists.add(informInfo6);
		lists.add(informInfo7);
		lists.add(informInfo8);
		for (InformInfo info : lists) {
			System.out.println(info);
		}
		System.out.println(lists);
	}
}


