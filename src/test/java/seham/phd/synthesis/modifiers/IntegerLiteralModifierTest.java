package seham.phd.synthesis.modifiers;

import java.io.File;		
import java.io.FileNotFoundException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;

import seham.phd.synthesis.visitors.MethodDeclarationVisitor;

public class IntegerLiteralModifierTest {
	
	final static String FILE_PATH = "src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
	static File file ;
	static MethodDeclarationVisitor visitor;
	static IntegerLiteralModifier modifier;
	
	@BeforeClass
	public static void setUpClass() throws FileNotFoundException {
		
		file = new File (FILE_PATH);

	}
	
	@Before
	public void setUp() {
		
		visitor = new MethodDeclarationVisitor();
		modifier = new IntegerLiteralModifier ();
	
	}
	
	@Test
	public void testVisit() throws FileNotFoundException {
		
		CompilationUnit cu = visitor.parse(file);
		modifier.visit(cu, null);
		System.out.println(cu.toString());
		
	}
	
	@After
	public void tearDown() {
	
	}
	
	@AfterClass
	public static void tearDownClass() {
		visitor = null;
		modifier = null;
	}

}
