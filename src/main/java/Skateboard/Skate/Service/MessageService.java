
package Skateboard.Skate.Service;

import Skateboard.Skate.Model.Message;
import Skateboard.Skate.Repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;

    
    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageRepository.getMessage(id);
    }

    public Message save(Message message) {
        if (message.getIdMessage() == null) {
            return messageRepository.save(message);
        } else {
            Optional<Message> message1 = messageRepository.getMessage(message.getIdMessage());
            if (message1.isPresent()) {
                return messageRepository.save(message);
            } else {
                return message;
            }
        }
    }
    
    
    public Message update(Message message){
        if(message.getIdMessage()!=null){
            Optional<Message>g= messageRepository.getMessage(message.getIdMessage());
            if(g.isPresent()){
                if(message.getMessageText()!=null){
                    g.get().setMessageText(message.getMessageText());
                }
                return messageRepository.save(g.get());
            }
        }
        return message;
    }
    
    
    public boolean deleteMessage(int id) {
       
        Optional<Message> c= messageRepository.getMessage(id);
        if(c.isPresent()){
            messageRepository.delete(c.get());
            return true;
        }
        return false;
    }

    
}

    

