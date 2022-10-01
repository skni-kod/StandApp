//
//  ListOfProjectsView.swift
//  iosApp
//
//  Created by RedinPCX on 14/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import shared


struct ListOfProjectsView: View {
    @StateObject var model = ProjectFilter()
    var body: some View {
        NavigationView{
            
            switch model.status {
            case is ResultInit<AnyObject>:
                Spacer().onAppear(perform: {
                    model.fetch()
                })
            case is ResultLoading<AnyObject>:
                ProgressView()
            case is ResultSuccess<AnyObject>:
                List(){
                    
                    ForEach(model.projects ?? [] , id: \.self){ data in
                        NavigationLink(destination: ProjectView(data: data)) {
                            VStack(){
                                
                                CardProjectView(title: data.title, sectionName: data.section.name, text: data.text)
                            }.frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
                            
                        }
                    }
                    
                }
                .listStyle(InsetListStyle())
                .navigationTitle( "Nasze projekty"  )
            default:
                Spacer()
            }
          
             
                
            
           
            
            
        }
        .navigationViewStyle(DefaultNavigationViewStyle())
        
        
    }
}

struct ListOfProjectsView_Previews: PreviewProvider {
    static var previews: some View {
        ListOfProjectsView()
    }
}
