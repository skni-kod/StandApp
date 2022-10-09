//
//  NewsStore.swift
//  iosApp
//
//  Created by RedinPCX on 06/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared
import SwiftUI

@MainActor
class ListOfNewsStoreImpl : ObservableObject  {
    @Published var article: [Article]?
    @Published var status:Result<AnyObject> = ResultInit()
    @Published var failure:KotlinThrowable?

    var repository = shared.KoinWrapper().getRepositoryArticle()
    
    func setUp(news:[Article]) {
        var change = news
        change=change.changeArray(value: { (ne:Article)->Article in
            let news =  Article(alias: ne.alias, authors: ne.authors, commentsNumber: ne.commentsNumber, creationDate: ne.creationDate, creator: ne.creator, gallery: ne.gallery, group: ne.group, id: ne.id, links: ne.links, publicationDate: ne.publicationDate, tags: ne.tags, text: ne.text.replacingOccurrences(of: "---readmore---", with: " "), title: ne.title)
  
            return news
        })
        self.article = change.filter(  {it in it.group == "Article"})
    }
    func fetch() async{
        status=ResultLoading()
        do{
            status = try await repository.getListOfArticles().onSuccess(action:{
                value in
                self.setUp(news: value as? [Article] ?? [])
                
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
