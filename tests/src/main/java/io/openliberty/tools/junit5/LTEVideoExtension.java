// package io.openliberty.tools.junit5;
//
// import static org.monte.media.FormatKeys.EncodingKey;
// import static org.monte.media.FormatKeys.FrameRateKey;
// import static org.monte.media.FormatKeys.KeyFrameIntervalKey;
// import static org.monte.media.FormatKeys.MediaTypeKey;
// import static org.monte.media.FormatKeys.MimeTypeKey;
// import static org.monte.media.VideoFormatKeys.CompressorNameKey;
// import static org.monte.media.VideoFormatKeys.DepthKey;
// import static org.monte.media.VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE;
// import static org.monte.media.VideoFormatKeys.QualityKey;
//
// import java.awt.Dimension;
// import java.awt.GraphicsConfiguration;
// import java.awt.GraphicsEnvironment;
// import java.awt.PageAttributes.MediaType;
// import java.awt.Rectangle;
// import java.io.File;
// import java.lang.reflect.Method;
// import java.text.Format;
// import java.util.Optional;
//
// import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
// import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
// import org.junit.jupiter.api.extension.ExtensionContext;
// import org.junit.platform.commons.util.AnnotationUtils;
// import org.monte.media.FormatKeys;
// import org.monte.media.math.Rational;
//
// import com.automation.remarks.video.enums.RecorderType;
// import com.automation.remarks.video.enums.RecordingMode;
// import com.automation.remarks.video.enums.VideoSaveMode;
// import com.automation.remarks.video.recorder.VideoConfiguration;
// import com.automation.remarks.video.recorder.monte.MonteScreenRecorder;
// import com.automation.remarks.video.recorder.monte.MonteScreenRecorderBuilder;
//
// public class LTEVideoExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
//
// private MonteScreenRecorder recorder;
//
// @Override
// public void beforeTestExecution(ExtensionContext context) throws Exception {
// if (!isVideoEnabled(context.getTestMethod().get())) {
// return;
// }
//
// GraphicsConfiguration gcfg = GraphicsEnvironment
// .getLocalGraphicsEnvironment().getDefaultScreenDevice()
// .getDefaultConfiguration();
// /*
// * return MonteScreenRecorderBuilder
// * .builder()
// * .setGraphicConfig(getGraphicConfig())
// * .setRectangle(captureSize)
// * .setFileFormat(fileFormat)
// * .setScreenFormat(screenFormat)
// * .setFolder(new File(videoConfiguration.folder()))
// * .setMouseFormat(mouseFormat).build();
// * Dimension screenSize = videoConfiguration.screenSize();
// * int width = screenSize.width;
// * int height = screenSize.height;
// * Rectangle captureSize = new Rectangle(0, 0, width, height);
// */
// int frameRate = new VideoConfigurationImpl().frameRate();
//
// Format fileFormat = new Format(MediaTypeKey, MediaType.VIDEO, MimeTypeKey, FormatKeys.MIME_AVI);
// Format screenFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey,
// ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
// CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
// DepthKey, 24, FrameRateKey, Rational.valueOf(frameRate),
// QualityKey, 1.0f,
// KeyFrameIntervalKey, 15 * 60);
// Format mouseFormat = new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
// FrameRateKey, Rational.valueOf(frameRate));
//
// Dimension screenSize = new VideoConfigurationImpl().screenSize();
// int width = screenSize.width;
// int height = screenSize.height;
//
// Rectangle captureSize = new Rectangle(0, 0, width, height);
//
// recorder = MonteScreenRecorderBuilder.builder()
// .setGraphicConfig(gcfg)
// .setRectangle(captureSize)
// .setFileFormat(fileFormat)
// .setScreenFormat(screenFormat)
// .setFolder(new File(new VideoConfigurationImpl().folder()))
// .setMouseFormat(mouseFormat)
// .build();
//
// System.out.println("SKSK: recorder =" + recorder);
// recorder.start();
// }
//
// class VideoConfigurationImpl implements VideoConfiguration {
//
// @Override
// public Boolean videoEnabled() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecordingMode mode() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public String remoteUrl() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public Boolean isRemote() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public String fileName() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public RecorderType recorderType() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public VideoSaveMode saveMode() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public int frameRate() {
// // TODO Auto-generated method stub
// return 0;
// }
//
// @Override
// public String ffmpegFormat() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public String ffmpegDisplay() {
// // TODO Auto-generated method stub
// return null;
// }
//
// @Override
// public String ffmpegPixelFormat() {
// // TODO Auto-generated method stub
// return null;
// }
// }
//
// @Override
// public void afterTestExecution(ExtensionContext context) throws Exception {
// if (!isVideoEnabled(context.getTestMethod().get())) {
// return;
// }
//
// String fileName = getFileName(context.getTestMethod().get());
// File video = stopRecording(fileName);
// if (context.getExecutionException().isPresent()) {
// doVideoProcessing(false, video);
// } else {
// doVideoProcessing(true, video);
// }
// }
//
// public static String doVideoProcessing(boolean successfulTest, File video) {
// String filePath = video != null ? video.getAbsolutePath() : null;
// if (!successfulTest || isSaveAllModeEnable()) {
// // logger.info("Video recording: " + filePath);
// return filePath;
// } else if (video != null && video.isFile()) {
// if (!video.delete()) {
// // logger.info("Video didn't deleted");
// return "Video didn't deleted";
// }
// // logger.info("No video on success test");
// }
// return "";
// }
//
// private static boolean isSaveAllModeEnable() {
// // TODO Auto-generated method stub
// return false;
// }
//
// private boolean isVideoEnabled(Method testMethod) {
// Optional<com.automation.remarks.video.annotations.Video> video = AnnotationUtils.findAnnotation(testMethod,
// com.automation.remarks.video.annotations.Video.class);
// return (video.orElse(null) != null);
// }
//
// /*
// * private boolean videoDisabled(Method testMethod) {
// * Optional<com.automation.remarks.video.annotations.Video> video = AnnotationUtils.findAnnotation(testMethod,
// * com.automation.remarks.video.annotations.Video.class);
// * return !videoEnabled(video.orElse(null));
// * }
// */
//
// private String getFileName(Method testMethod) {
// String methodName = testMethod.getName();
// LTEVideo video = testMethod.getAnnotation(LTEVideo.class);
// return getVideoFileName(video, methodName);
// }
//
// private static String getVideoFileName(LTEVideo annotation, String methodName) {
// if (annotation == null) {
// return methodName;
// }
// String name = annotation.name();
// return name.length() > 1 ? name : methodName;
// }
//
// private File stopRecording(String filename) {
// File video = null;
// if (recorder != null) {
// try {
// video = recorder.saveAs(filename);
// } catch (IndexOutOfBoundsException ex) {
// throw new IllegalStateException("Video recording wasn't started");
// }
// }
// return video;
// }
// }