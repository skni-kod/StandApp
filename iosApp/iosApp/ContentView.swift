import WebKit
import SwiftUI
import shared




@MainActor struct ContentView: View {
   var greet = {
        try await shared.KoinWrapper().greet()
    }
    @State var text = "custom"
	var body: some View {
        NavigationView()
         
        /*ZStack{
            HTMLStringView(htmlContent: "<p>\(text)</p>")
                .onAppear{
                    getData()
                }
                .frame(width:.infinity,height: .infinity)
        }
	}
   
    func getData(){
        print("adsa1")
        Task {
            print("adsa2")
            let test = try await greet()
            text=test.value?.get(index: 19)?.text ?? "Test"
         
          
            }
    */
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

struct HTMLStringView: UIViewRepresentable {
    let htmlContent: String

    func makeUIView(context: Context) -> WKWebView {
        return WKWebView()
    }

    func updateUIView(_ uiView: WKWebView, context: Context) {
        uiView.loadHTMLString(htmlContent, baseURL: nil)
    }
}
