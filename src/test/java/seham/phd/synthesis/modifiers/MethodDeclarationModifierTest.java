package seham.phd.synthesis.modifiers;

import java.io.File;	
import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.visitor.Visitable;

import seham.phd.synthesis.visitors.MethodDeclarationVisitor;

public class MethodDeclarationModifierTest {
	
	final static String FILE_PATH = "src/main/java/seham/phd/synthesis/code/concise/JFrameExample.java";
	static File file ;
    static MethodDeclarationVisitor visitor;
    static MethodDeclarationModifier modifier;
	
    @BeforeClass
	public static void setUpClass() throws FileNotFoundException {
		
		file = new File (FILE_PATH);

	}
    
	@Before
	public void setUp() {
		
		visitor = new MethodDeclarationVisitor();
		modifier = new MethodDeclarationModifier ();
	
	}
	
	
//	@Test
	public void testClone() throws FileNotFoundException {
		
		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu , null);
		visitor.locateUtilityMethods(); 
		Visitable clonedBody = modifier.cloneBody(visitor.getUtilityMethods().get(0));
		System.out.println(clonedBody.toString());
		
	}
	
	@Test
	public void testModify () throws IOException {
		
		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu , null);
		
		visitor.locateUtilityMethods();
		visitor.locateDocumentationMethods();
		
		modifier.modifyAllDocMethods(cu, visitor);
		
		System.out.println(cu.toString());
		
		
	}
	
//	@Test
	public void testRemoveNode() throws FileNotFoundException {
		
		CompilationUnit cu = visitor.parse(file);
		visitor.visit(cu , null);
		
		visitor.locateUtilityMethods();
		visitor.locateDocumentationMethods();
		
		modifier.removeNode(visitor.getDocumentationMethods().get(0).getBody().get(), 0);
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
