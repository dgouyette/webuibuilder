package org.webuibuilder

import groovytools.builder.PropertyException
import junit.framework.TestCase
import org.exoplatform.commons.utils.WriterPrinter
import org.exoplatform.webui.application.WebuiRequestContext
import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class WebUIBuilderTest extends TestCase{

  WebUIBuilder mb

  WriterPrinter writer

 
  
  void setUp() {  
    WebuiRequestContext context = mock(WebuiRequestContext.class)
    UIMockedComponent uicomponent = new UIMockedComponent(context)
    
    //Write to console
    writer = new WriterPrinter(new PrintWriter(System.out, false))
    when(context.getWriter()).thenReturn(writer);

    mb =new WebUIBuilder(context, uicomponent)
    mb.setContext(context)
  }

  /**
   * Tout va bien rien ne manque
   */
  void testFormCorrect() {
        def aForm = mb.build {
          form{
             formItem (name: 'name', label : 'name', type: 'text', validate: 'not-empty' , jcrPath: "/node")
             formItem (name: 'calendarField', label : 'calendarField', type: 'calendar', validate: 'not-empty' , jcrPath: "/node")
          }
        }

  }




  //TODO Find a way to valid than output is xhtml valid 
  void testResultatXmlValide(){}


 

  void testAttributManquantFormItem(){
   try{
      def aForm = mb.build {
          form{
             formItem ( label : 'Article.dialog.label.name', type: 'text', validate: 'not-empty' , jcrPath: "/node")
          }
        }
   }
    catch (PropertyException e){
      assert e.message.equals("Property 'form.collections.formItemList.formItem.mergedProperties.name': property required")
    }
  }

  /**
   * Test que si le type de formItem n'est pas reference, cela ne fonctionne pas
   */
  void testTypeDeFormItemIncorrect(){
    try{  
    def aForm = mb.build {
          form{
             formItem ( label : 'Article.dialog.label.name', type: 'inexistant', validate: 'not-empty' , jcrPath: "/node")
          }
        }
    }
  catch (PropertyException e){
    assert e.message.equals("Property 'form.collections.formItemList.formItem.mergedProperties.type': value invalid")   
  }
  }


  public void tearDown(){
    writer.flush()
  }


}
