package org.webuibuilder

import org.exoplatform.ecm.webui.component.explorer.popup.actions.UIDocumentForm
import org.exoplatform.webui.application.WebuiRequestContext

/**
* MocketWebUi Component fot Unit tests
**/
class UIMockedComponent {

  String name
  String type
  String[] args
  String[] actions=["Save", "Cancel"]

  private WebuiRequestContext context

  UIDocumentForm uiparent
  List<UIMockedComponent> children


  def UIMockedComponent() {}

  def UIMockedComponent(newId, newType) {
        name= newId
        type= newType
  }


  def event(String action){
      return "javascript:eXo.webui.UIForm.submitForm('siteExplorer#UIDocumentForm','$action',true)" 

  }

  def UIMockedComponent(WebuiRequestContext newContext){
    context =  newContext
  }

  /**
  *  dynamicly add method
  **/
  Object invokeMethod(String what, Object who){
    //println "what : $what, who : $who"

    if (!what.startsWith("add") || !what.endsWith("Field")){
      throw new IllegalArgumentException("$what ($who) n'est pas supporte par ce composant")
    }


    //extract component type wthout add & field
    this.type= what.replace("add","").replace("Field","")
    this.name= who[0]
    this.args = who[2]

    processRender()
  }

  void begin(){
     context.getWriter().write("\r<form>")
  }

  void end(){
    context.getWriter().write("\r</form>")
  }


  void processRender() {
    context.getWriter().write(toString())
  }

  public String toString() {
    return "UIMockedComponent{name=$name, type=$type, args=$args}";
  }
}
