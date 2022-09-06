import SwiftUI
import shared

@main
struct iOSApp: App {
    init(){
        shared.KoinWrapper.companion.doInitKoin()
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}
