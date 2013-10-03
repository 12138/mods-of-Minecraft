package mapnpc.common.misc;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import mapnpc.common.MapNPC;
import mapnpc.common.task.TaskSavedData;
import net.minecraft.world.World;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class DialogManager {

	public static DialogManager instance = new DialogManager();
	public Document dialogDoc = null;
	public Map<String, NPC> dialogs = new HashMap<String, NPC>();

	private DialogManager() {

	}

	public NPC getCharacter(String name) {
		return this.dialogs.get(name);

	}

	public void loadDialogXML(World world) {

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			File file = new File(MapNPC.dir,Resources.DIR_DIALOG);
			File[] files = file.listFiles(new FilenameFilter() {

				public boolean accept(File file, String fileName) {
					if (fileName.endsWith(".xml")) {
						return true;
					}
					return false;
				}
			});
			if(files==null)
				return;
			for (File dialogFile : files) {
				dialogDoc = dBuilder.parse(dialogFile);
				Element eleDialogs = dialogDoc.getDocumentElement();
				NodeList charList = eleDialogs
						.getElementsByTagName("character");
				for (int i = 0; i < charList.getLength(); i++) {
					Element eleChar = (Element) charList.item(i);
					NPC character = new NPC();
					character.setName(eleChar.getAttribute("name"));
					NodeList diaList = eleChar.getElementsByTagName("dialog");
					for (int j = 0; j < diaList.getLength(); j++) {
						Element eleDia = (Element) diaList.item(j);
						NPC.Dialog dialog = character.new Dialog();
						NodeList dialogConList = eleDia
								.getElementsByTagName("context");
						dialog.setId(eleDia.getAttribute("id"));
						dialog.setTo(eleDia.getAttribute("to"));
						dialog.setContent(dialogConList.item(0).getFirstChild()
								.getNodeValue());

						NodeList opList = eleDia.getElementsByTagName("option");
						for (int k = 0; k < opList.getLength(); k++) {
							Element eleOp = (Element) opList.item(k);
							NPC.Dialog.Option option = dialog.new Option();
							option.setId(eleOp.getAttribute("id"));
							option.setTo(eleOp.getAttribute("to"));
							if (eleOp.getAttribute("taskGroup") != "") {

								TaskSavedData.forWorld(world).addTasktoGroupKey(eleOp.getAttribute("taskGroup"),false, false);
								option.setHasTask();
								option.setTask(eleOp.getAttribute("taskGroup"));
							}
							if (eleOp.getAttribute("reply") != "") {
								option.setHasReply();
								option.setReply(eleOp.getAttribute("reply"));
							}
							NodeList conList = eleOp
									.getElementsByTagName("context");
							for (int l = 0; l < conList.getLength(); l++) {

								Element eleCon = (Element) conList.item(l);
								option.setContent(eleCon.getFirstChild()
										.getNodeValue());

							}
							dialog.setOptions(option.getId(), option);
							dialog.optionNumPlus();

						}
						character.setDialog(dialog.getId(), dialog);

					}

					dialogs.put(character.getName(), character);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	
}