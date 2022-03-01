package seham.phd.synthesis.visitors;

import static org.junit.Assert.assertEquals;		
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;

public class MethodDeclarationVisitorTest {

	// usually static, so it could be instantiate in the static setUp method
	final static String FILE_PATH = "src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
	static File file;
	static MethodDeclarationVisitor visitor;
	
	/**
	 * code executed before all test methods, executed once only, e.g DB connection
	 * or instance creation
	 * @throws FileNotFoundException
	 */
	@BeforeClass
	public static void setUpClass() throws FileNotFoundException {

		file = new File(FILE_PATH);

	}
	
	/**
	 * code executed before each test methods, code that needed to be executed
	 * repeatedly before each method
	 */
	@Before
	public void setUp() {

		visitor = new MethodDeclarationVisitor();

	}

	@Test
	public void testParse() throws FileNotFoundException {

		CompilationUnit cu = visitor.parse(file);
		if (cu != null) {
			System.out.println("File Parsed Correctly! \n ");
		}

//		System.out.println(cu.toString());

	}

	@Test
	public void testVisit() throws FileNotFoundException {

		int size = 4;

		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu, null);
		int actualSize = visitor.getAllMethodDeclarations().size();
		assertEquals(size, actualSize);
		System.out.println("Total Number of Method Declarations: " + actualSize);

	}

	@Test
	public void testGetUtilityMethods() throws FileNotFoundException {

		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu, null);
		visitor.locateUtilityMethods();
		int actualSize = visitor.getUtilityMethods().size();
		assertTrue(actualSize == 2);
		System.out.println("Number of Utility Methods: " + actualSize);

	}

	@Test
	public void testGetDocumentationMethods() throws FileNotFoundException {

		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu, null);
		visitor.locateDocumentationMethods();
		int actualSize = visitor.getDocumentationMethods().size();
		assertTrue(actualSize == 2);
		System.out.println("Number of Documentation Methods: " + actualSize);

	}
	
	@Test 
	public void testPrintUsingDot () throws IOException {
		
		CompilationUnit cu = visitor.parse(file);
		visitor.printUsingDot(cu);
	}

	/**
	 * code executed after each test method, code executed repeatedly after other
	 test methods
	 */
	@After
	public void tearDown() {

	}

	/**
	 * code executed after all test methods, executed once, e.g DB disconnection,
	 * clean up test environment, making instance equal null after using it.
	 */
	@AfterClass
	public static void tearDownClass() {
		visitor = null;
	}

}
