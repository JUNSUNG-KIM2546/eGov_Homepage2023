package egovframework.com.cmm.web;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

import egovframework.com.cmm.SessionVO;
import egovframework.com.cmm.service.EgovFileMngService;
import egovframework.com.cmm.service.FileVO;
import egovframework.let.utl.fcc.service.EgovNumberUtil;
import egovframework.let.utl.fcc.service.EgovStringUtil;
import egovframework.rte.fdl.property.EgovPropertyService;

import net.coobird.thumbnailator.Thumbnails;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Class Name : EgovImageProcessController.java
 * @Description :
 * @Modification Information
 *
 *    수정일       수정자         수정내용
 *    -------        -------     -------------------
 *    2009. 4. 2.     이삼섭
 *    2011.08.31.     JJY        경량환경 템플릿 커스터마이징버전 생성
 *
 * @author 공통 서비스 개발팀 이삼섭
 * @since 2009. 4. 2.
 * @version
 * @see
 *
 */
@Controller
public class EgovImageProcessController extends HttpServlet {

    /**
	 *  serialVersion UID
	 */
	private static final long serialVersionUID = -6339945210971171173L;

	@Resource(name = "EgovFileMngService")
    private EgovFileMngService fileService;
	
	@Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(EgovImageProcessController.class);

    /**
     * 첨부된 이미지에 대한 미리보기 기능을 제공한다.
     *
     * @param atchFileId
     * @param fileSn
     * @param sessionVO
     * @param model
     * @param response
     * @throws Exception
     */
    @SuppressWarnings("resource")
	@RequestMapping("/cmm/fms/getImage.do")
    public void getImageInf(SessionVO sessionVO, ModelMap model, @RequestParam Map<String, Object> commandMap, HttpServletResponse response) throws Exception {

		//@RequestParam("atchFileId") String atchFileId,
		//@RequestParam("fileSn") String fileSn,
		String atchFileId = (String)commandMap.get("atchFileId");
		String fileSn = (String)commandMap.get("fileSn");

		FileVO vo = new FileVO();

		vo.setAtchFileId(atchFileId);
		vo.setFileSn(fileSn);

		FileVO fvo = fileService.selectFileInf(vo);

		//String fileLoaction = fvo.getFileStreCours() + fvo.getStreFileNm();

		File file = new File(fvo.getFileStreCours(), fvo.getStreFileNm());
		FileInputStream fis = null; new FileInputStream(file);

		BufferedInputStream in = null;
		ByteArrayOutputStream bStream = null;
		try{
			fis = new FileInputStream(file);
			in = new BufferedInputStream(fis);
			bStream = new ByteArrayOutputStream();
			int imgByte;
			while ((imgByte = in.read()) != -1) {
			    bStream.write(imgByte);
			}

			String type = "";

			if (fvo.getFileExtsn() != null && !"".equals(fvo.getFileExtsn())) {
			    if ("jpg".equals(fvo.getFileExtsn().toLowerCase())) {
				type = "image/jpeg";
			    } else {
				type = "image/" + fvo.getFileExtsn().toLowerCase();
			    }
			    type = "image/" + fvo.getFileExtsn().toLowerCase();

			} else {
				LOGGER.debug("Image fileType is null.");
			}

			response.setHeader("Content-Type", type);
			response.setContentLength(bStream.size());

			bStream.writeTo(response.getOutputStream());

			response.getOutputStream().flush();
			response.getOutputStream().close();


		}catch(Exception e){
			LOGGER.debug("{}", e);
		}finally{
			if (bStream != null) {
				try {
					bStream.close();
				} catch (Exception est) {
					LOGGER.debug("IGNORED: {}", est.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (Exception ei) {
					LOGGER.debug("IGNORED: {}", ei.getMessage());
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception efis) {
					LOGGER.debug("IGNORED: {}", efis.getMessage());
				}
			}
		}
    }
    
    // 썸네일 가져오기
    @RequestMapping("/cmm/fms/getThumbImage.do")
    public void getThumbImage(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	//EgovStringUnit => egovframework.let.utl.fcc.service.EgovStringUtil
    	String fileStorePath = EgovStringUtil.isEmpty(request.getParameter("fileStorePath")) ? "board.fileStorePath" : request.getParameter("fileStorePath");
    	String atchFileNm = request.getParameter("atchFileNm");
    	String thumbYn = request.getParameter("thumbYn");
    	String fileExt = "";
    	int index = atchFileNm.lastIndexOf(".");
    	if(index != -1) {
    		fileExt = atchFileNm.substring(index + 1);
    		atchFileNm = atchFileNm.substring(0, index);
    	}
    	
    	String resFilePath = propertiesService.getString(fileStorePath);
    	File file = null;
    	if ("Y".equals(thumbYn)) {
    		String strWidth = request.getParameter("width") == null ? propertiesService.getString("photoThumbWidth") : request.getParameter("width");
    		String strHeight = request.getParameter("height") == null ? propertiesService.getString("photoThumbHeight") : request.getParameter("height");
    		int width = (EgovNumberUtil.getNumberValidCheck(strWidth)) ? EgovStringUtil.zeroConvert(strWidth) : propertiesService.getInt("photoThumbWidth");
    		int height = (EgovNumberUtil.getNumberValidCheck(strHeight)) ? EgovStringUtil.zeroConvert(strHeight) : propertiesService.getInt("photoThumbHeight");
    		
    		file = new File(resFilePath, atchFileNm + "_THUMB." + fileExt);
    		if (!file.exists()) {
    			File orgFile = new File(resFilePath, atchFileNm);
    			if (orgFile.exists()) {
    				Thumbnails.of(orgFile).size(width, height).toFile(file);
    			}
    			else {
    				LOGGER.info("File Not Found : " + resFilePath + File.separator + atchFileNm);
    			}
    		}
    	}
    	else {
    		file = new File(resFilePath, atchFileNm);
    	}
    	if(file.exists()) {
    		FileInputStream fis = null;
    		BufferedInputStream in = null;
    		ByteArrayOutputStream bStream = null;
    		
    		try {
    			fis = new FileInputStream(file);
    			in = new BufferedInputStream(fis);
    			bStream = new ByteArrayOutputStream();
    			
    			int imgByte;
    			while((imgByte = in.read()) != -1) {
    				bStream.write(imgByte);
    			}
    			String type = "";
    			if (fileExt != null && !"".equals(fileExt)) {
    				if ("jpg".equals(EgovStringUtil.lowerCase(fileExt))) {
    					type = "image/j[eg";
    				}
    				else {
    					LOGGER.debug("Image fileType is null.");
    				}
    			}
    			else {
    				LOGGER.debug("Image fileType is null.");
    			}
    				
    			response.setHeader("Content-type", type);
    			response.setContentLength(bStream.size());
    				
    			bStream.writeTo(response.getOutputStream());
    			
    			response.getOutputStream().flush();
    				
    		}	
    		catch (FileNotFoundException fnfe) {
				LOGGER.debug("/cmm/fms/getImage.do -- stream error : " + atchFileNm);
			}
    		catch (IOException ioe) {
				LOGGER.debug("/cmm/fms/getImage.do -- stream error : " + atchFileNm);
			}
    		catch (Exception e) {
				LOGGER.debug("/cmm/fms/getImage.do -- stream error : " + atchFileNm);
			}
    		finally {
    			try {response.getOutputStream().close();}	catch (Exception ex) {}
    			if(bStream != null) {
    				try {bStream.close();}	catch (IOException ex) {LOGGER.info("IOException");}
    			}
    			
    			if(in != null) {
    				try {in.close();}	catch (IOException ex) {LOGGER.info("IOException");}
    			}
    				
    			if(fis != null) {
    				try {fis.close();}	catch (IOException ex) {LOGGER.info("IOException");}
    			}
    		}
    	}
    }
}

