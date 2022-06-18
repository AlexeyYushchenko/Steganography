package model.exceptions;

public class InsufficientTextSizeException extends Exception{
    public InsufficientTextSizeException(){
        super("Не хватает текста для секретного сообщения. Либо уменьшите секретное сообщение, либо возьмите больший текст.");
    }
}
