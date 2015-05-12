package swust.edu.cn.threeExaminations.util;

public class TestThread implements Runnable{

	test_diaoyong t = new test_diaoyong();
	public void run() {
		//t.print2();
		UtilTest tt = new UtilTest();
    	tt.tt1();
	    }
	public void p(){
		System.out.println("fsd");
	}
}
