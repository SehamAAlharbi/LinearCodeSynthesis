package seham.phd.synthesis.visitors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MethodDeclarationVisitorTest {
	
	// usually static, so it could be instantiate in the static setUp method
	static MethodDeclarationVisitor visitor;
	final static String FILE_PATH = "src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
	static File file ;
	
	// code executed before all test methods, executed once only, e.g DB connection or instance creation
	@BeforeClass
	public static void setUpClass() throws FileNotFoundException {
		visitor = new MethodDeclarationVisitor();
	    file = new File (FILE_PATH);
		visitor.parse(file);
	}
	
	// code executed before each test methods, code that needed to be executed repeatedly before each method
	@Before
	public void setUp() {
	
	}
	
	@Test
	public void testParse() throws FileNotFoundException {
		
		int size = 4;
		int actualSize = visitor.getAllMethodDeclarations().size ();
		assertEquals(size,actualSize);
		System.out.println("Total Number of Method Declarations: " + actualSize);
		
	}
	
	@Test
	public void testGetUtilityMethods() throws FileNotFoundException {
		
		int actualSize = visitor.getUtilityMethods().size();
		assertTrue(actualSize == 2);
		System.out.println("Number of Utility Methods: " +  actualSize );
		
	}
	
	@Test
	public void testGetDocumentationMethods() throws FileNotFoundException {
		
		int actualSize = visitor.getDocumentationMethods().size();
		assertTrue(actualSize == 2);
		System.out.println("Number of Documentation Methods: " +  actualSize );
		
	}
	
	// code executed after each test method, code executed repeatedly after other test methods
	@After
	public void tearDown() {
	
	}
	
	@AfterClass
	// code executed after all test methods, executed once, e.g DB disconnection, clean up test environment, making instance equal null after using it.
	public static void tearDownClass() {
		visitor = null;
	}

}
