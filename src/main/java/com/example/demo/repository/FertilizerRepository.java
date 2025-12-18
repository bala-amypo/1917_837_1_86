public interface FertilizerRepository extends JpaRepository<Fertilizer, Long> {
    List<Fertilizer> findByRecommendedForCropsContaining(String cropName);
}
