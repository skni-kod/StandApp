//
//  ListOfNewsView.swift
//  iosApp
//
//  Created by RedinPCX on 14/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct ListOfNewsView: View {
    let hardwareScreenSize = UIScreen.main.bounds.size
    var body: some View {
        ZStack {
            Rectangle()
                .fill(Color.blue).ignoresSafeArea()
               
                Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
                .frame(width: hardwareScreenSize.width*0.80 )
                   .padding()
                   .background(Color.white)
                   .clipShape(RoundedRectangle(cornerRadius: 25.0, style: .continuous))
                   .padding()
                   .shadow(color: Color.black.opacity(0.2), radius: 5, x: 0, y: 2)
                   
            
            
    
        }
    }
}

struct ListOfNewsView_Previews: PreviewProvider {
    static var previews: some View {
        ListOfNewsView()
    }
}
