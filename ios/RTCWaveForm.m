#import "RCTWaveForm.h"

@implementation RCTWaveForm

- (void)initialize
{ 
  self.waveform = [[FDWaveformView alloc] initWithFrame:CGRectZero];
  // self.waveform.delegate = self;
  self.waveform.alpha = 1.0f;
  self.waveform.progressSamples = 10000;
  self.waveform.doesAllowScrubbing = NO;
  self.waveform.doesAllowStretch = NO;
  self.waveform.doesAllowScroll = NO;
  self.waveform.wavesColor = [UIColor whiteColor];
  [self addSubview:self.waveform];
}

- (id)initWithFrame:(CGRect)rect{
  if(self = [super initWithFrame:rect]){
    [self initialize];
  }
  return self;
}

- (void)reactSetFrame:(CGRect)frame
{
  self.frame = frame;
  self.waveform.frame = frame;
}

- (void)setBase64Content:(NSString*)base64Content
{
  NSString *UUID = [[NSUUID UUID] UUIDString];
  NSString *tempFilePath = [NSTemporaryDirectory() stringByAppendingPathComponent:[NSString stringWithFormat: @"%@.m4a", UUID]];
  NSFileManager *manager = [NSFileManager defaultManager];
  NSData *decodedData = [[NSData alloc] initWithBase64EncodedString:base64Content options:0];
  [manager createFileAtPath:tempFilePath contents:decodedData attributes:nil];
  [self setUrl:[NSURL fileURLWithPath:tempFilePath]];
}

- (void)setFilename:(NSString*)strFilename
{
  NSString *docPath = [NSSearchPathForDirectoriesInDomains (NSDocumentDirectory,NSUserDomainMask, YES) objectAtIndex:0];
  NSString *filePath = [NSString stringWithFormat:@"%@/%@",docPath,strFilename];
  NSURL *url = [NSURL fileURLWithPath:filePath];
  [self setUrl: url];
}

- (void)setUrl:(NSURL*)url
{
  [self.waveform setAudioURL:url];
}

@end
