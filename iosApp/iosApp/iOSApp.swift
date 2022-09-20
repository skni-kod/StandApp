import SwiftUI
import shared

@main
struct iOSApp: App {
    init(){
        shared.KoinWrapper.companion.doInitKoin()
        //UITableView.appearance().backgroundColor = .cyan
  
    }
	var body: some Scene {
		WindowGroup {
            NavigationTabView()
		}
	}
}

