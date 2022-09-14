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
        HStack{
           
            List(listOfProjects , id: \.id){ data in
                    Text("\(data.title)")
                
            }
            //Text(listOfProjects?[0].title ?? "test")
        }.onAppear{
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
