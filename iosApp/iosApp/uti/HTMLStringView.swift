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
    @Binding var dynamicHeight: CGFloat
    var webview: WKWebView = WKWebView()
    let text:String
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
 
    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        webView.navigationDelegate = context.coordinator    // << delegate !!
        return webView
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        let htmlStart1 = "<meta name='viewport' content='width=device-width, shrink-to-fit=YES' initial-scale='1.0' maximum-scale='1.0' minimum-scale='1.0' user-scalable='no'></HEAD><BODY>"
        let htmlEnd1 = ""
        
        let htmlStart = "<div style=\"font-family: -apple-system,BlinkMacSystemFont,Segoe UI,Roboto,Oxygen,Ubuntu,Cantarell,Open Sans,Helvetica Neue,sans-serif\">"
        let htmlEnd = "</div>"
        
        uiView.loadHTMLString( htmlStart1 + text + htmlEnd1 , baseURL: nil)
    }
    
    class Coordinator: NSObject, WKNavigationDelegate {
        var parent: HTMLStringView
        
        init(_ parent: HTMLStringView) {
            self.parent = parent
        }
        
        func webView(_ webView: WKWebView, didFinish navigation: WKNavigation!) {
            /*let css = "body { background-color : #ffffff }"
             let js = "var style = document.createElement('style'); style.innerHTML = '\(css)'; document.head.appendChild(style);"
             
             webView.evaluateJavaScript(js, completionHandler: nil)   // << here !!*/
            
            
            if webView.isLoading == false {
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
                    webView.evaluateJavaScript("document.readyState", completionHandler: { (complete, error) in
                        if complete != nil {
                            webView.evaluateJavaScript("document.documentElement.scrollHeight", completionHandler: { (height, error) in
                                if error == nil {
                                    guard let height = height as? CGFloat else { return }
                                    webView.frame.size.height = height
                                    self.parent.dynamicHeight = height as! CGFloat
                                    webView.sizeToFit()
                                }
                            })
                        }
                    })
                }
            }
            
            
        }
    }
}




struct WebView : UIViewRepresentable {
    
    @Binding var offset: CGPoint
    var contentInset: UIEdgeInsets = .zero
    let text:String
    class Coordinator: NSObject, UIScrollViewDelegate {
        var parent: WebView
        
        init(_ parent: WebView) {
            self.parent = parent
        }
        
        func scrollViewDidScroll(_ scrollView: UIScrollView) {
            var offset = scrollView.contentOffset
            offset.y += self.parent.contentInset.top
            self.parent.offset = offset
        }
    }
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIView(context: Context) -> WKWebView {
        let webview = WKWebView()
        webview.scrollView.delegate = context.coordinator
        // continue setting up webview content
        return webview
    }
    
    func updateUIView(_ uiView: WKWebView, context: Context) {
        let htmlStart1 = "<meta name='viewport' content='width=device-width, shrink-to-fit=YES' initial-scale='1.0' maximum-scale='1.0' minimum-scale='1.0' user-scalable='no'>"
        let htmlEnd1 = ""
        if uiView.scrollView.contentInset != self.contentInset {
            uiView.scrollView.contentInset = self.contentInset
        }
        uiView.loadHTMLString( htmlStart1 + text + htmlEnd1 , baseURL: nil)
        
    }
    
}
