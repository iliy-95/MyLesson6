package com.example.mylesson6.data;


import com.google.firebase.Timestamp;

import java.util.HashMap;
import java.util.Map;

public class CardDataTranslate {
public static class Fields{
    public static final String Content = "content";
    public static final String S= "s";
    public static final String LIKE = "like";
    public static final String IM = "im";
    public static final String DATE = "date";
}

public static CardData DocumentToCardData(String id, Map<String,Object> doc) {
    CardData answer = new CardData((String) doc.get(Fields.S),
            (String) doc.get(Fields.Content),
            ImIndexConverter.getImByIndex((Integer)
                    doc.get(Fields.IM)),
            (boolean) doc.get(Fields.LIKE),
            ( (Timestamp) doc.get(Fields.DATE) ).toDate());
    answer.setId(id);
    return answer;

}

public static Map<String,Object> cardDataDocument(CardData cardData){
    Map<String,Object> answer = new HashMap<>();
    answer.put(Fields.S,cardData.getS());
    answer.put(Fields.Content,cardData.getContent());
    answer.put(Fields.IM,ImIndexConverter.getImByIndex(cardData.getIm()));
    answer.put(Fields.LIKE,cardData.isLike());
    answer.put(Fields.DATE,cardData.getDate());
    return answer;
}

}
