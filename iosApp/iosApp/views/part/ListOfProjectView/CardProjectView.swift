//
//  CardProjectView.swift
//  iosApp
//
//  Created by RedinPCX on 20/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

struct CardProjectView: View {
    let hardwareScreenSize = UIScreen.main.bounds.size
    
    var title:String
    var sectionName:String
    var text:String
    var textReadmore = "---readmore---"
    var limitLine=2
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(.linearGradient(colors: [Color(hex: 0x36D1DC), Color(hex: 0x5B86E5)], startPoint: .topLeading, endPoint: .bottomTrailing))
                .shadow(radius: 10)
               
            VStack {
                HStack{
                    Spacer()
                    Text("\(sectionName)")
                        .padding()
                        .font(.system(size: 8))
                }
         
                Text("\(title)")
                    .font(.title2)
                    .lineLimit(1)
                  
                
                if let range = text.range(of: textReadmore) {
                  let substring = text[..<range.lowerBound] // or str[str.startIndex..<range.lowerBound]
                    Text(substring)
                        .padding()
                        .font(.system(size: 10))
                        .lineLimit(limitLine)
                      
                }
                else {
                    Text("\(text)")
                        .font(.system(size: 10))
                        .lineLimit(limitLine)
                        .padding()
                }
          
            }
            .multilineTextAlignment(.center)
            .foregroundColor(.white)
        }
        .frame(width: hardwareScreenSize.width * 0.8)
    }
    
    
}

struct CardProjectView_Previews: PreviewProvider {
    static var previews: some View {
        CardProjectView(title: "test", sectionName: "test", text: "test")
    }
}
