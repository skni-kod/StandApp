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
    @StateObject var model = ListOfProjectStoreImpl()
    @State var errorShow = false
     let maxAttemptConnect = 2
    @State var attempt : Int = 0
    var body: some View {
        NavigationView{
            
            switch model.status {
            case is ResultInit<AnyObject>:
                Spacer().onAppear(perform: {
                    Task{
                         await model.fetch()
                    }
                })
            case is ResultLoading<AnyObject>:
                ProgressView()
            case is ResultSuccess<AnyObject>:
                List(){
                    
                    ForEach(model.projects ?? [] , id: \.self){ data in
                        NavigationLink(destination: ProjectView(data: data)) {
                            VStack(){
                                
                                ItemProjectView(title: data.title, sectionName: data.section.name, text: data.text)
                            }.frame(minWidth: 0, maxWidth: .infinity, alignment: .center)
                        
                            
                        }
                    }
                    
                }
                .listStyle(InsetListStyle())
                .navigationTitle( LocalizedStringKey("our_projects") )

                    
            default:
                if (model.failure != nil && attempt < maxAttemptConnect)  {
                        
                    Text("")
                        .alert(isPresented: $errorShow,content:  {
                                                Alert(
                                                    title: Text(LocalizedStringKey("error")),
                                                    message: Text(model.failure?.message ?? "404"),
                                                    dismissButton: .default(Text(  attempt < maxAttemptConnect-1 ? LocalizedStringKey("try_again") : LocalizedStringKey("cancel") )) {
                                                        errorShow=false
                                                        attempt =  1 + attempt
                                                         Task{
                                                            await model.fetch()
                                                       }
                                                    }
                                                )
                        })
                        .onAppear{
                            errorShow=true
                        }
                }
               
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
