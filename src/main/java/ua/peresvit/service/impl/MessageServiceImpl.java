package ua.peresvit.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ua.peresvit.dao.ChatRepository;
import ua.peresvit.dao.MessageRepository;
import ua.peresvit.dto.ChatWithLastMessage;
import ua.peresvit.entity.Chat;
import ua.peresvit.entity.Message;
import ua.peresvit.entity.User;
import ua.peresvit.service.MessageService;
import ua.peresvit.service.UserService;

import java.sql.Timestamp;
import java.util.*;

@Service
public class MessageServiceImpl implements MessageService{

    final MessageRepository messageRepository;
    final ChatRepository chatRepository;
    final MessageSource messages;
    final UserService userService;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, ChatRepository chatRepository, MessageSource messages, UserService userService) {
        this.messageRepository = messageRepository;
        this.chatRepository = chatRepository;
        this.messages = messages;
        this.userService = userService;
    }

    @Override
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Chat saveChat(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public Chat saveDialog(User[] users) {
        Chat newChat = new Chat();
        Set<User> members = new HashSet<>(Arrays.asList(users));
        newChat.setMembers(members);
        return saveChat(newChat);
    }

    @Override
    public Chat findOneChat(Long arg0) {
        return chatRepository.findOne(arg0);
    }

    @Override
    public Chat findDialog(User user) {
        Set<Chat> dialogs = chatRepository.findDialogsOfUser(user);
        for (Chat c : dialogs
             ) {
            if (c.getMembers().contains(user)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public Chat createNewChat(Chat chat, Locale locale) {
        User creator = userService.getCurrentUser();
        chat.setOwner(creator);
        chat = saveChat(chat);
//            Adding message about chat creation
        sendMessage(creator, getNewChatCreatingMessage(creator, chat, locale));
        return chat;
    }

    @Override
    public Message getNewChatCreatingMessage(User creator, Chat chat, Locale locale) {
        Message message = new Message();
        message.setSender(creator);
        message.setChat(chat);
        message.setFunctional(true);
        message.setContent(creator.getFirstName() + " " + creator.getLastName() + " "
                + messages.getMessage("message.newChatCreationEvent", null, locale) + " "
                + "<" + chat.getChatTitle() + ">");
        return message;
    }

    @Override
    public Chat addNewMembersToChat(LinkedList<User> membersToAdd, Chat chat) {
        Set<User> members = chat.getMembers();
        for (User u : membersToAdd) {
            members.add(u);
        }
        chat.setMembers(members);
        return saveChat(chat);
    }

    @Override
    public Chat deleteMemberFromChat(User user, Chat chat) {
        Set<User> members = chat.getMembers();
        members.remove(user);
        chat.setMembers(members);
        return chatRepository.save(chat);
    }


    @Override
    public Message getAddingNewMemberMessage(User adder, LinkedList<User> listOfNewMembers, Chat chat, Locale locale) {
        Message message = new Message();
        message.setSender(adder);
        message.setChat(chat);
        message.setFunctional(true);
//      adding names of added members
        Iterator<User> it = listOfNewMembers.iterator();
        StringBuilder newMembers = new StringBuilder();
        for (int i = 0; i != 1;) {
            User u = it.next();
            newMembers.append(u.getFirstName()).append(" ").append(u.getLastName());
            if (! it.hasNext()){
                i = 1;
            } else {
                newMembers.append(',').append(' ');
            }
        }
        message.setContent(adder.getFirstName() + " " + adder.getLastName() + " "
                + messages.getMessage("message.addingNewMembersEvent", null, locale) + " "
                + newMembers);
        return message;
    }

    @Override
    public Message sendMessage(User from, Message message) {
        message.setSender(from);
        message.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return saveMessage(message);
    }

//    @Override
//    public LinkedList<Message> findAllByAuthorAndReceiver(User author, User receiver) {
//        return findAllByAuthorAndReceiver(author, receiver);
//    }

    @Override
    public List<Message> findMessagesByChatOrderByCreatedAt(Long chatId) {
        return messageRepository.findByChatOrderByCreatedAt(chatId);
    }

    @Override
    public Set<Chat> findChatsOfUsers(User user) {
        Set<Chat> chats = chatRepository.findChatsOfUser(user);
        Map<Chat, Message> map = new HashMap<>();
        for (Chat chat: chats) {
            if (chat.getMembers().size()==2) {
                for (User u:chat.getMembers()) {
                    if (!u.equals(user)) {
                        chat.setChatTitle(u.getFirstName() + " " +u.getLastName());
                    }
                }
            }
            map.put(chat, findLastMessage(chat));
        }
        return chats;
    }

    @Override
    public Set<ChatWithLastMessage> findCustomChatsOfUser(User user) {
        return chatRepository.getChatsWithLastMessage(user);
    }

    @Override
    public Message findLastMessage(Chat chat) {
//        return messageRepository.findLastMessage(chat);
        return  null;
    }

    @Override
    public long countUnreadChats() {
        User currentUser = userService.getCurrentUser();
//        Add to repo method to count
        Set<ChatWithLastMessage> chats = chatRepository.getChatsWithLastMessage(currentUser);
        Long count = 0L;
        for (ChatWithLastMessage c: chats) {
            if(!c.isReadStatus()) {
                count++;
            }
        }
        return count;
    }
}
