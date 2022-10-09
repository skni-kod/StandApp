//
//  NewsView.swift
//  iosApp
//
//  Created by RedinPCX on 06/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import WebKit
import SwiftUI
import shared
import CommonMark
import MarkdownUI

struct NewsView: View {
    let data:Article
    @State var offset: CGPoint = .zero
    @State private var webViewHeight: CGFloat = 0
    let markDownHtml : MarkDownHtml
    init(data:Article){
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

struct NewsView_Previews: PreviewProvider {
    static var previews: some View {
        EmptyView()
    }
}
