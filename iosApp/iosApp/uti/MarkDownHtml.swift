//
//  MarkdownResolver.swift
//  iosApp
//
//  Created by RedinPCX on 01/10/2022.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Markdown



class MarkDownHtml{
    let pattern = #"<div(.*?)>[\s\S]*?<\/div(.*?)>"#
    var text:String
    var html:String=""
    var markdown :String=""
    init(text:String){
        self.text=text
        for i in getMatches(regex: pattern, inputText: text){
            if let i = i[0] {
                self.html += i
            }
            
        }
        markdown=replaceRegex(text: text, pattern: pattern, template: "" )
    }

    func findSrcs(_ content: String) -> Substring? {
        
        let ranges = content.range(of: pattern, options: .regularExpression)
        let matches = ranges.map { content[$0] }
        
        return matches
    }
    func replaceRegex(text:String, pattern:String, template:String) -> String{
    
        let regex = try! NSRegularExpression(pattern: pattern, options: NSRegularExpression.Options.caseInsensitive)
        let range = NSMakeRange(0, text.count)
        let modString = regex.stringByReplacingMatches(in: text, options: [], range: range, withTemplate: template)
        return modString
    }
    func getMatches(regex: String, inputText: String) -> [[String?]]{

        guard let regex = try? NSRegularExpression(pattern: regex) else {
            return []
        }
        let results = regex.matches(in: inputText,
                                range: NSRange(inputText.startIndex..., in: inputText))

        let finalResult = results.map { match in

            return (0..<match.numberOfRanges).map { range -> String? in

                let rangeBounds = match.range(at: range)
                guard let range = Range(rangeBounds, in: inputText) else {
                    return nil
                }
                return String(inputText[range])
            }
        }.filter { !$0.isEmpty }


        return finalResult
    }


}
