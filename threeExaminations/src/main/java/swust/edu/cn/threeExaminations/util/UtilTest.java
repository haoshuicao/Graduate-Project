package swust.edu.cn.threeExaminations.util;

/**
 * @author Shaw Joe
 * @Title: 
 * @ClassName：UtilTest.java
 * @Package: swust.edu.cn.threeExaminations.util
 * @Description: TODO
 * @author Shao Zhou
 * @createDate:2013  下午10:42:45    
 * @email:shaozhou@swust.edu.cn
 * @phone:15881615397
 * @Department:Knowledge engineering and Data Mining Lab of Computer Science and Technology Academy of SWUST
 * @Address:Southwest University of  Science and Technology 59 Qinglong Road, Mianyang, 621010, P.R.China
 * @reviseNote:
 * @version:V1.0
 */
public class UtilTest {

	/**
	 * @Title: main   UtilTest
	 * @Description: TODO
	 * @param @param args
	 * @return void
	 * @throws
	 */
	public void tt(){
		for (int i = 0; i < 100; i++) {
            System.out.println("A"+i);
        }
	}
	public void tt1(){
		for (int i = 0; i < 100; i++) {
            System.out.println("B"+i);
        }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BatchInsertRecordThread h1=new BatchInsertRecordThread("线程A");
        Thread demo= new Thread(h1);
        TestThread h2=new TestThread();
        Thread demo1=new Thread(h2);
        demo.start();
        demo1.start();
        System.out.println("我 是 主函数！！");
	}
}
