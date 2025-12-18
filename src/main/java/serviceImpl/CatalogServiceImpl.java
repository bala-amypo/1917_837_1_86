@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {

    private final CropRepository cropRepo;
    private final FertilizerRepository fertRepo;

    public Crop addCrop(Crop crop) {
        if (crop.getSuitablePHMin() > crop.getSuitablePHMax())
            throw new BadRequestException("PH min");

        if (!ValidationUtil.validSeason(crop.getSeason()))
            throw new BadRequestException("Invalid season");

        return cropRepo.save(crop);
    }

    public Fertilizer addFertilizer(Fertilizer f) {
        if (!f.getNpkRatio().matches("\\d+-\\d+-\\d+"))
            throw new BadRequestException("NPK");

        return fertRepo.save(f);
    }

    public List<Crop> findSuitableCrops(Double ph, Double water, String season) {
        return cropRepo.findSuitableCrops(ph, water, season);
    }

    public List<Fertilizer> findFertilizersForCrops(List<String> crops) {
        return crops.stream()
                .flatMap(c -> fertRepo.findByRecommendedForCropsContaining(c).stream())
                .distinct()
                .toList();
    }
}
