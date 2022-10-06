//
//  NavigationView.swift
//  iosApp
//
//  Created by RedinPCX on 14/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct NavigationTabView: View {
    var body: some View {
        TabView {
            ListOfProjectsView( )
                .tabItem {
                    Label(LocalizedStringKey("projects"), systemImage: "pencil.circle")
                }
            
            ListOfArticlesView()
                .tabItem {
                    Label(LocalizedStringKey("articles"), systemImage: "note.text")
                }
            
            ListOfNewsView()
                .tabItem {
                    Label(LocalizedStringKey("news"), systemImage: "square.and.arrow.up")
                }
            
            UserView()
                .tabItem {
                    Label(LocalizedStringKey("user"), systemImage: "gearshape")
                }
        }
        .onAppear{
            if #available(iOS 15.0, *) {
                let appearance = UITabBarAppearance()
                UITabBar.appearance().scrollEdgeAppearance = appearance
            }
        }
        
    }
}

struct NavigationView_Previews: PreviewProvider {
    static var previews: some View {
        NavigationTabView()
    }
}
