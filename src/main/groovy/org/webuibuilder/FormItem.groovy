package org.webuibuilder

import org.exoplatform.commons.utils.WriterPrinter
import java.text.SimpleDateFormat
import org.exoplatform.webui.form.UIForm
import org.exoplatform.ecm.webui.form.UIDialogForm
import org.exoplatform.ecm.webui.utils.DialogFormUtil

class FormItem {
  def name
  def label
  def type
  def validate
  def jcrPath
  def visible
  def editable

  static void processTimestamp(WriterPrinter out, uiForm){
    DialogFormUtil util

    
     String[] fieldName = ["jcrPath=/node",  "editable=if-null", "validate=empty,name", ""]
     out.write """
            <tr>
              <td class="FieldLabel">Nom</td>
              <td class="FieldComponent">"""
             uiForm.addTextField("name","name", fieldName) ;
             out.write """</td>
           </tr> """

  }



  void processRender(WriterPrinter out, uiForm, FormItem formItem){

    List<String> args = new ArrayList<String>();
   
    formItem.metaPropertyValues.each {
      //TODO review this condition
      if (it.value!=null){
      switch (it.name){

        //Liste des proprietes prise en compte pour le moment
        case ["jcrPath","validate","editable",  ]:
          args.add("$it.name=$it.value")
           break
      }
      }

    }

    out.write """
            <tr>
              <td class="FieldLabel">$formItem.label</td>
              <td class="FieldComponent">"""
              switch (formItem.type){
                case "text":
                  uiForm.addTextField(formItem.name,formItem.label,args as String[])
                  break
                case "textarea" : 
                  uiForm.addTextAreaField(formItem.name,formItem.label,args as String[])
                  break
                case "calendar":
                  uiForm.addCalendarField(formItem.name, formItem.label, args as String[])
                  break
                case "wysiwyg":
                  uiForm.addWYSIWYGField(formItem.name, formItem.label, args as String[])
                  break
              }
    out.write """</td>
           </tr> """


  }


  public String toString() {
    return "\n  FormItem{" +
            "name=" + name+
            ", label=" + label +
            ", type=" + type +
            ", validate=" + validate +
            ", jcrPath=" + jcrPath +
            ", visible=" + visible +
            ", editable=" + editable +
            '}';
  }
}
