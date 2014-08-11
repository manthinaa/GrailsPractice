package test.anil.grails

import org.apache.commons.lang.StringUtils

import test.anil.Chat

class ChatController {

	def index() {
	}

	def join(String nickname) {
		if(StringUtils.isBlank(nickname.trim())){
			redirect(action:'index')
		}
		else{
			println "hi dude"
			session.nickname = nickname
			render (view: 'chat')
		}
	}

	def retrieveLatestMessages() {
		def messages = Chat.listOrderByDate(order: 'desc', max:10)
		[messages:messages.reverse()]
	}
	
	def submitMessage(String message) {
		new Chat(nickname: session.nickname, message:message).save()
		render "<script>retrieveLatestMessages()</script>"
	}
}
