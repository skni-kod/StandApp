//
//  HTMLStringView.swift
//  iosApp
//
//  Created by RedinPCX on 20/09/2022.
//  Copyright © 2022 orgName. All rights reserved.
//


import WebKit
import SwiftUI

struct HTMLStringView: UIViewRepresentable {
    let htmlContent: String

    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator    // << delegate !!
        return webView
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        let htmlStart = "<div style=\"padding: 40px; font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Open Sans,Helvetica Neue,sans-serif\">"
        let htmlEnd = "</div>"
        uiView.loadHTMLString(htmlStart + htmlContent + htmlEnd, baseURL: nil)
    }

    class Coordinator: NSObject, WKNavigationDelegate {

        func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
            let css = "body { background-color : #ffffff }"
            let js = "var style = document.createElement('style'); style.innerHTML = '\(css)'; document.head.appendChild(style);"
            
            webView.evaluateJavaScript(js, completionHandler: nil)   // << here !!
        }
    }

    func makeCoordinator() -> Coordinator {
        Coordinator()
    }
}

