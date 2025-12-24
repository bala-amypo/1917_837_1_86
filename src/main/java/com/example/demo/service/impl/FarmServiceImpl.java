public Farm createFarm(Farm f, Long ownerId) {
    User owner = userRepo.findById(ownerId)
            .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

    if (f.getSoilPH() < 3 || f.getSoilPH() > 10)
        throw new IllegalArgumentException("Invalid pH");

    f.setOwner(owner);
    return repo.save(f);
}
