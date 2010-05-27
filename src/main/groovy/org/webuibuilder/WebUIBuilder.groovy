package org.webuibuilder

import groovytools.builder.MetaBuilder
import org.exoplatform.commons.utils.WriterPrinter
import org.exoplatform.webui.application.WebuiRequestContext


class WebUIBuilder {

  
  def WriterPrinter out
  def WebuiRequestContext context
  def MetaBuilder mb

  
  
  //On ne type pas l'objet, cela va servir pour les tests
  def uiForm



  def build(Closure closure){
    
     Form f = mb.build(closure)
     f.processRender out, uiForm

    }

  //Constructeur par defaut
  WebUIBuilder(){
    context = WebuiRequestContext.getCurrentInstance()
    init()
  }


  //Constructeur utilise par les IHM
  WebUIBuilder(newUiComponent){
    context = WebuiRequestContext.getCurrentInstance()
    uiForm = newUiComponent
    init()
  }

  /**
   * Constructeur pour les tests
   */
  WebUIBuilder(WebuiRequestContext newContext,  newUiComponent){
    this.context = newContext
    this.uiForm= newUiComponent
    init()
  }


  def init() {

    mb = new MetaBuilder()
    out = context.getWriter();

    

    mb.define {
      //Defini le schema de l'objet form
      form(factory: Form) {
        

        //L'objet form contient une liste de formItem : au minimum 1
        collections {
          formItemList(min: 1) {
            //un objet form contient une list d'objet formItem dont le scheme est defini ci dessous
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
          //Check verifie la liste des valeurs autorisee pour la propriete type
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
