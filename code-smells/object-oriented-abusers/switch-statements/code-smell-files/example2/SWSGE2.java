import java.util.*;

class CosmeticSampleVariation {
  private final String sampleId;
  private final TestType testType;
  private final Map<String, Double> results = new LinkedHashMap<>();

  public CosmeticSampleVariation(String sampleId, TestType testType) {
    this.sampleId = sampleId;
    this.testType = testType;
  }

  public String getSampleId() {
    return sampleId;
  }

  public TestType getTestType() {
    return testType;
  }

  public void recordResult(String metric, double value) {
    results.put(metric, value);
  }

  public Map<String, Double> getResults() {
    return Collections.unmodifiableMap(results);
  }
}

class CosmeticLabVariation {
  private final boolean isCertified;
  private final Map<TestType, TestExecutorVariation> executors = new EnumMap<>(TestType.class);

  public CosmeticLabVariation(boolean isCertified) {
    this.isCertified = isCertified;
    executors.put(TestType.PH_BALANCE, new PhBalanceExecutorVariation());
    executors.put(TestType.OIL_CONTENT, new OilContentExecutorVariation());
    executors.put(TestType.MOISTURE_LEVEL, new MoistureLevelExecutorVariation());
    executors.put(TestType.UV_PROTECTION, new UvProtectionExecutorVariation());
    executors.put(TestType.STABILITY_UNDER_HEAT, new HeatStabilityExecutorVariation());
    executors.put(TestType.FRAGRANCE_INTENSITY, new FragranceIntensityExecutorVariation());
    executors.put(TestType.COLOR_UNIFORMITY, new ColorUniformityExecutorVariation());
    executors.put(TestType.PARTICLE_SIZE_DISTRIBUTION, new ParticleSizeDistributionExecutorVariation());
  }

  public void performTest(CosmeticSampleVariation sample) {
    if (!isCertified) {
      System.out.println("Lab is not certified to run tests.");
      return;
    }

    System.out.println("Running " + sample.getTestType()
      + " on sample " + sample.getSampleId());

    TestExecutorVariation executor = executors.get(sample.getTestType());
    if (executor != null) {
      executor.execute(sample);
    } else {
      System.out.println("Unknown test type: " + sample.getTestType());
    }

    System.out.println("Results: " + sample.getResults());
  }
}

interface TestExecutorVariation  {
  void execute(CosmeticSampleVariation sample);
}

class PhBalanceExecutorVariation  implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.PH_SEED_OFFSET);
    double reading = TestConstants.PH_MIN + rand.nextDouble() * TestConstants.PH_RANGE;
    double value = Math.round(reading * TestConstants.PH_PRECISION) / TestConstants.PH_PRECISION;
    sample.recordResult("pH", value);
  }
}

class OilContentExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.OIL_SEED_OFFSET);
    double perc = TestConstants.OIL_MIN + rand.nextDouble() * TestConstants.OIL_RANGE;
    double value = Math.round(perc * TestConstants.OIL_PRECISION) / TestConstants.OIL_PRECISION;
    sample.recordResult("Oil %", value);
  }
}

class MoistureLevelExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.MOISTURE_SEED_OFFSET);
    double moisture = TestConstants.MOISTURE_MIN + rand.nextDouble() * TestConstants.MOISTURE_RANGE;
    double value = Math.round(moisture * TestConstants.MOISTURE_PRECISION) / TestConstants.MOISTURE_PRECISION;
    sample.recordResult("Moisture %", value);
  }
}

class UvProtectionExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.UV_SEED_OFFSET);
    int spf = TestConstants.SPF_MIN + rand.nextInt(TestConstants.SPF_RANGE);
    sample.recordResult("SPF Value", spf);
  }
}

class HeatStabilityExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.HEAT_SEED_OFFSET);
    double degradation = rand.nextDouble() * TestConstants.HEAT_MAX_DEGRADATION;
    double value = Math.round(degradation * TestConstants.HEAT_PRECISION) / TestConstants.HEAT_PRECISION;
    sample.recordResult("Degradation %", value);
  }
}

class FragranceIntensityExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.FRAGRANCE_SEED_OFFSET);
    double score = TestConstants.FRAGRANCE_MIN + rand.nextDouble() * TestConstants.FRAGRANCE_RANGE;
    double value = Math.round(score * TestConstants.FRAGRANCE_PRECISION) / TestConstants.FRAGRANCE_PRECISION;
    sample.recordResult("Scent Score", value);
  }
}

class ColorUniformityExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation  sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.COLOR_SEED_OFFSET);
    double deltaE = TestConstants.COLOR_MIN + rand.nextDouble() * TestConstants.COLOR_RANGE;
    double value = Math.round(deltaE * TestConstants.COLOR_PRECISION) / TestConstants.COLOR_PRECISION;
    sample.recordResult("ΔE", value);
  }
}

class ParticleSizeDistributionExecutorVariation implements TestExecutorVariation  {
  @Override
  public void execute(CosmeticSampleVariation  sample) {
    Random rand = new Random(sample.getSampleId().hashCode() ^ TestConstants.PARTICLE_SEED_OFFSET);
    double size = TestConstants.PARTICLE_MIN + rand.nextDouble() * TestConstants.PARTICLE_RANGE;
    double value = Math.round(size * TestConstants.PARTICLE_PRECISION) / TestConstants.PARTICLE_PRECISION;
    sample.recordResult("Avg Particle Size (µm)", value);
  }
}

enum TestTypeVariation  {
  PH_BALANCE,
  OIL_CONTENT,
  MOISTURE_LEVEL,
  UV_PROTECTION,
  STABILITY_UNDER_HEAT,
  FRAGRANCE_INTENSITY,
  COLOR_UNIFORMITY,
  PARTICLE_SIZE_DISTRIBUTION
}

final class TestConstantsVariation  {
  private TestConstantsVariation () {}
  static final long PH_SEED_OFFSET = 0L;
  static final double PH_MIN = 4.5;
  static final double PH_RANGE = 3.5;
  static final double PH_PRECISION = 100.0;

  static final long OIL_SEED_OFFSET = 0xDEADBEEFL;
  static final double OIL_MIN = 5.0;
  static final double OIL_RANGE = 15.0;
  static final double OIL_PRECISION = 10.0;

  static final long MOISTURE_SEED_OFFSET = 0xCAFEBABEL;
  static final double MOISTURE_MIN = 3.0;
  static final double MOISTURE_RANGE = 9.0;
  static final double MOISTURE_PRECISION = 10.0;

  static final long UV_SEED_OFFSET = 0x12345678L;
  static final int SPF_MIN = 15;
  static final int SPF_RANGE = 36;

  static final long HEAT_SEED_OFFSET = 0x87654321L;
  static final double HEAT_MAX_DEGRADATION = 5.0;
  static final double HEAT_PRECISION = 100.0;

  static final long FRAGRANCE_SEED_OFFSET = 0xABCDEF01L;
  static final double FRAGRANCE_MIN = 1.0;
  static final double FRAGRANCE_RANGE = 9.0;
  static final double FRAGRANCE_PRECISION = 10.0;

  static final long COLOR_SEED_OFFSET = 0x10FEDCBAL;
  static final double COLOR_MIN = 0.5;
  static final double COLOR_RANGE = 2.5;
  static final double COLOR_PRECISION = 100.0;

  static final long PARTICLE_SEED_OFFSET = 0x0FEDCBA1L;
  static final double PARTICLE_MIN = 0.1;
  static final double PARTICLE_RANGE = 4.9;
  static final double PARTICLE_PRECISION = 100.0;
}

class SSGE2 {
  public static void main(String[] args) {
    CosmeticLabVariation  lab = new CosmeticLabVariation (true);
    List<CosmeticSampleVariation > samples = Arrays.asList(
      new CosmeticSampleVariation ("SMP-001", TestType.PH_BALANCE),
      new CosmeticSampleVariation ("SMP-002", TestType.OIL_CONTENT),
      new CosmeticSampleVariation ("SMP-003", TestType.MOISTURE_LEVEL),
      new CosmeticSampleVariation ("SMP-004", TestType.UV_PROTECTION),
      new CosmeticSampleVariation ("SMP-005", TestType.STABILITY_UNDER_HEAT),
      new CosmeticSampleVariation ("SMP-006", TestType.FRAGRANCE_INTENSITY),
      new CosmeticSampleVariation ("SMP-007", TestType.COLOR_UNIFORMITY),
      new CosmeticSampleVariation ("SMP-008", TestType.PARTICLE_SIZE_DISTRIBUTION)
    );

    for (CosmeticSampleVariation sample : samples) {
      lab.performTest(sample);
      System.out.println("---");
    }
  }
}
