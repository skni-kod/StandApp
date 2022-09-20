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
struct ProjectView: View {
    let data:Project
    init(data:Project){
        self.data=data
        UINavigationBar.appearance().titleTextAttributes = [.font : UIFont(name: "Arial", size: 16)!]
    }
    var body: some View {
        HTMLStringView(htmlContent: "<p>\(data.text)</p>").navigationBarTitle(  data.title , displayMode: .inline )
    }
}

struct ProjectView_Previews: PreviewProvider {
    static var previews: some View {
        Text("test")
       // ProjectView(text:"sdfds")
    }
}
