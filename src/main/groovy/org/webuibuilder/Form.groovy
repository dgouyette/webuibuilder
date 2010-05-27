package org.webuibuilder
import org.exoplatform.commons.utils.WriterPrinter

class Form {
  def formItemList = []

  
     void processRender(WriterPrinter out, uicomponent){

       
       out.write """<div class="UIForm">"""
       uicomponent.begin()
       out.write """
            <div class="HorizontalLayout">
              <table class="UIFormGrid">
            """
            org.webuibuilder.FormItem.processTimestamp (out, uicomponent)
            this.formItemList.each{
              it.processRender(out, uicomponent, it)
            }
      out.write """
              </table>"""
              //Rendu du bouton
              Button.processRender(out, uicomponent)
        out.write """</div>"""
        uicomponent.end()
        out.write "</div>"

     }


  public String toString() {
    return "Form{" +
            "id=" + id +
            ", title=" + title +
            ", formItemList=" + formItemList +
            '}';

    
  }
}
