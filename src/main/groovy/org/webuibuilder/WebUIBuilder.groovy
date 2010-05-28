package org.webuibuilder

import groovytools.builder.MetaBuilder
import org.exoplatform.commons.utils.WriterPrinter
import org.exoplatform.webui.application.WebuiRequestContext


class WebUIBuilder {

  
  def WriterPrinter out
  def WebuiRequestContext context
  def MetaBuilder mb


  def uiForm



  def build(Closure closure){
    
     Form f = mb.build(closure)
     f.processRender out, uiForm

    }

  //default constructor
  WebUIBuilder(){
    context = WebuiRequestContext.getCurrentInstance()
    init()
  }


  //Used in real life
  WebUIBuilder(newUiComponent){
    context = WebuiRequestContext.getCurrentInstance()
    uiForm = newUiComponent
    init()
  }

  //For test
  WebUIBuilder(WebuiRequestContext newContext,  newUiComponent){
    this.context = newContext
    this.uiForm= newUiComponent
    init()
  }


  def init() {

    mb = new MetaBuilder()
    out = context.getWriter();

    

    mb.define {
      //define form shema
      form(factory: Form) {
        

        //Form must contain at least one item of formItem 
        collections {
          formItemList(min: 1) {

            //form contains an object list formItem, schema defined bellow
            formItem(schema: 'formItem')
          }
        }
      }
    }

    mb.define {
      formItem(factory: FormItem) {
        properties {
          name(req: true)
          label(req: true)
          type(req: true, check: ['text','textarea',  'password', 'calendar', 'wysiwyg'])
          validate(req: true, check: ['not-empty', 'name', 'email', 'number', 'empty', 'datetime'])
          jcrPath(req: false)
          visible(req: false, check: [true, false], def: true)
          editable(req: false, check: [true, false, 'if-not-null', 'if-null'], def: true)
        }
      }
    }
  }

  


}
