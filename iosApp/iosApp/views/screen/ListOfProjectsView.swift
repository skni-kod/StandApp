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
    let repo = shared.KoinWrapper().getRepositoryProject()
   
   
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
                try await repo.getListOfProjects().onSuccess(action:{
                    value in
                    var custom = value as? [Project] ?? []
                   let change = custom.changeArray(value: { (pro:Project)->Project in
                        let project=Project(authors: pro.authors, creationDate: pro.creationDate, creator: pro.creator, gallery: pro.gallery, id: pro.id, links: pro.links, publicationDate: pro.publicationDate, section: pro.section, text: pro.text.replacingOccurrences(of: "---readmore---", with: " "), title: pro.title)
                        return project
                    })
                    listOfProjects = change
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
