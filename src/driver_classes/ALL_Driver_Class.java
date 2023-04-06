package driver_classes;

import java.io.IOException;

//import test_Class.get_tc;
import test_Class.patch_tc;
import test_Class.post_tc1;
import test_Class.put_tc;

public class ALL_Driver_Class {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		post_tc1.orchestrator();
		put_tc.orchestrator();
		patch_tc.orchestrator();
		//get_tc.orchestrator();
	}

}