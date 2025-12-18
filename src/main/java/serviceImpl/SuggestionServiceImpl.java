@Service
@RequiredArgsConstructor
public class SuggestionServiceImpl implements SuggestionService {

    private final FarmRepository farmRepo;
    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;
    private final SuggestionRepository sugRepo;

    public Suggestion generateSuggestion(Long farmId) {
        Farm farm = farmRepo.findById(farmId)
                .orElseThrow(() -> new ResourceNotFoundException("Farm"));

        List<Crop> crops = cropRepo.findSuitableCrops(
                farm.getSoilPH(), farm.getWaterLevel(), farm.getSeason());

        String cropNames = crops.stream().map(Crop::getName).collect(Collectors.joining(","));

        String fertNames = crops.stream()
                .flatMap(c -> fertRepo.findByRecommendedForCropsContaining(c.getName()).stream())
                .map(Fertilizer::getName)
                .distinct()
                .collect(Collectors.joining(","));

        return sugRepo.save(Suggestion.builder()
                .farm(farm)
                .suggestedCrops(cropNames)
                .suggestedFertilizers(fertNames)
                .build());
    }

    public Suggestion getSuggestion(Long id) {
        return sugRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Suggestion"));
    }

    public List<Suggestion> getSuggestionsByFarm(Long farmId) {
        return sugRepo.findByFarmId(farmId);
    }
}
