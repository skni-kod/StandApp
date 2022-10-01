//
//  ProjectsInteractor.swift
//  iosApp
//
//  Created by RedinPCX on 01/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared



extension Array {
    mutating func changeArray<T>( value: @escaping (T) -> T) -> Array<Element>{
        for (index, element) in self.enumerated() {
            let changedValue = value(element as! T)

                self[index] = changedValue as! Element
            }
        
        return self
    }
   
}
