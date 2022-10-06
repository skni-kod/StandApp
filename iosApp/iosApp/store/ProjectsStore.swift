//
//  ProjectsInteractor.swift
//  iosApp
//
//  Created by RedinPCX on 01/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI


@MainActor
class ListOfProjectStoreImpl : ObservableObject {
    @Published var projects: [Project]?
    @Published var status:Result<AnyObject> = ResultInit()
    @Published var failure:KotlinThrowable?

    var repository = shared.KoinWrapper().getRepositoryProject()
    
    func setUp(projects:[Project]) {
        var change = projects
        change=change.changeArray(value: { (pro:Project)->Project in
            let project=Project(authors: pro.authors, creationDate: pro.creationDate, creator: pro.creator, gallery: pro.gallery, id: pro.id, links: pro.links, publicationDate: pro.publicationDate, section: pro.section, text: pro.text.replacingOccurrences(of: "---readmore---", with: " "), title: pro.title)
            return project
        })
        self.projects = change
    }
    func fetch() async{
        status=ResultLoading()
        do{
            status = try await repository.getListOfProjects().onSuccess(action:{
                value in
                self.setUp(projects: value as? [Project] ?? [])
                
            })
            .onFailure(action: {
                value in self.failure=value
            })
        }
        
        catch {
            status=ResultError(throwable: error as! KotlinThrowable )
        }
           
          
       
    }
}
