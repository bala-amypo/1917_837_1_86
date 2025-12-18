public interface SuggestionService {
    Suggestion generateSuggestion(Long farmId);
    Suggestion getSuggestion(Long suggestionId);
    List<Suggestion> getSuggestionsByFarm(Long farmId);
}
