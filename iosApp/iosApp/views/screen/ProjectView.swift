//
//  ProjectView.swift
//  iosApp
//
//  Created by RedinPCX on 14/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import WebKit
import SwiftUI
import shared
import CommonMark
import MarkdownUI

struct ProjectView: View {
    let data:Project
    @State var offset: CGPoint = .zero
    @State private var webViewHeight: CGFloat = 0
    let markDownHtml : MarkDownHtml
    init(data:Project){
        self.data=data
        UINavigationBar.appearance().titleTextAttributes = [.font : UIFont(name: "Arial", size: 16)!]
        markDownHtml = MarkDownHtml(text: data.text)

    }
    var body: some View {
      
        ScrollView{
           
            Markdown(markDownHtml.markdown)
          
            HTMLStringView(dynamicHeight: $webViewHeight, text: markDownHtml.html)
                            .padding(.horizontal)
                            .frame(height: webViewHeight)
            
            
        }
       
    }
}

struct ProjectView_Previews: PreviewProvider {
    static var previews: some View {
        Text("test")
       // ProjectView(text:"sdfds")
    }
}
