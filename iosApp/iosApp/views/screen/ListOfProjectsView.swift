//
//  ListOfProjectsView.swift
//  iosApp
//
//  Created by RedinPCX on 14/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared

@MainActor
struct ListOfProjectsView: View {
    
    // meybe transfer to usecase?
    var getProjects = {
        try await shared.KoinWrapper().greet()
    }
    @State var listOfProjects : [Project] = []
    var body: some View {
        NavigationView{
            
            
            
            List(){
                
                ForEach(listOfProjects , id: \.self){ data in
                    NavigationLink(destination: ProjectView(data: data)) {
                        VStack(){
                            
                            CardProjectView(title: data.title, sectionName: data.section.name, text: data.text)
                        }.frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
                        
                    }
                }
                
            }
            .listStyle(InsetListStyle())
            .navigationTitle( "Nasze projekty"  )
            
        }
        .navigationViewStyle(DefaultNavigationViewStyle())
        .onAppear{
            
            Task{
                try await getProjects().onSuccess(action:{
                    value in listOfProjects = value as? [Project] ?? []
                    
                })
            }
        }
        
    }
}

struct ListOfProjectsView_Previews: PreviewProvider {
    static var previews: some View {
        ListOfProjectsView()
    }
}
