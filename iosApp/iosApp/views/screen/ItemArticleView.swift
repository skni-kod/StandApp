//
//  ItemArticleView.swift
//  iosApp
//
//  Created by RedinPCX on 09/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import Foundation

struct ItemArticleView: View {
    let hardwareScreenSize = UIScreen.main.bounds.size
    
    var title:String
    var date:String
    var text:String
    var textReadmore = "---readmore---"
    var timeStepSign = "T"
    var limitLine=2
    var body: some View {
        ZStack {
            RoundedRectangle(cornerRadius: 25, style: .continuous)
                .fill(.linearGradient(colors: [Color(hex: 0x36D1DC), Color(hex: 0x5B86E5)], startPoint: .topLeading, endPoint: .bottomTrailing))
                .shadow(radius: 10)
               
            VStack {
                HStack{
                    Spacer()
                    if let dataRange = date.range(of: timeStepSign) {
                        let substring = date[..<dataRange.lowerBound]
                        Text(substring)
                            .padding()
                            .font(.system(size: 8))
                    }
                }
                Text("\(title)")
                    .lineLimit(2)
                    .minimumScaleFactor(0.5)
                    .padding()
                
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


