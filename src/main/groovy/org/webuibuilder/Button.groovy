package org.webuibuilder

import org.exoplatform.commons.utils.WriterPrinter




class Button {

  static void processRender(WriterPrinter out, uicomponent){
    out.write """
     <div class="UIAction" style="display:block;">
      <table class="ActionContainer" align="center">
            <tr>
                <td align="center">"""
                        for(action in uicomponent.getActions()) {
                             static link =uicomponent.event(action) 
                  out.write """<a href="$link" class="ActionButton LightBlueStyle">
                                    <div class="ButtonLeft">
                                        <div class="ButtonRight">
                                            <div class="ButtonMiddle">
                                                $action
                                            </div>
                                        </div>
                                    </div>
                                </a>
                      """
                        }

                out.write"""
                </td>
            </tr>
        </table>
    </div>
    """
  }

}
