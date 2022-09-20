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
                    Label("Editor", systemImage: "pencil.circle")
                    Text("Editor")
                }
            
            ListOfNewsView()
                .tabItem {
                    Label("Notes", systemImage: "note.text")
                    Text("Notes")
                }
            
            ListOfNewsView()
                .tabItem {
                    Label("Share", systemImage: "square.and.arrow.up")
                    Text("Share")
                }
            
            ListOfNewsView()
                .tabItem {
                    Label("Settings", systemImage: "gearshape")
                    Text("Settings")
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
